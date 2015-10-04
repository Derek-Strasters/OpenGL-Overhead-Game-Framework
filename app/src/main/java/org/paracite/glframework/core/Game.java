package org.paracite.glframework.core;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

@SuppressLint("Registered")
public class Game extends Activity implements Renderer {
	enum GLGameState {
		Initialized,
		Running,
		Paused,
		Finished,
		Idle
		}
	
	GLSurfaceView glView;
	
	public Graphics graphics;
	public Audio audio;
	public Input input;
	public FileIO fileIO;
	
	Screen screen;
	
	GLGameState state = GLGameState.Initialized;
	Object stateChanged = new Object();
	long startTime = System.nanoTime();
	WakeLock wakeLock;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		glView = new GLSurfaceView(this);
		glView.setRenderer(this);
		glView.setId(1618);
		setContentView(glView);		

		graphics = new Graphics(glView);
		fileIO = new FileIO(getAssets());
		audio = new Audio(this);
		input = new Input(this, glView, 1, 1);
		
		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,"GLGame");
	}

	@Override
	protected void onPause() {
		
		synchronized (stateChanged) {
			if (state == GLGameState.Running) {
				if (isFinishing())
					state = GLGameState.Finished;
				else
					state = GLGameState.Paused;
				while (true) {
					try {
						stateChanged.wait();
						break;
					} catch (InterruptedException e) {
					}
				}
			}
		}
		wakeLock.release();
		glView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		glView.onResume();
		wakeLock.acquire();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
	}
	
	@Override
	protected void onDestroy() { 
		audio.release();
		super.onDestroy();
	}

	public void onDrawFrame(GL10 arg0) {
		GLGameState state = null;
		synchronized (stateChanged) {
			state = this.state;
		}
		
		switch (state) {
		case Running:
			float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
			startTime = System.nanoTime();
			screen.update(deltaTime);
			screen.present(deltaTime);
			break;
		case Paused:
			screen.pause();
			synchronized (stateChanged) {
				this.state = GLGameState.Idle;
				stateChanged.notifyAll();
			}
			break;
		case Finished:
			screen.pause();
			screen.dispose();
			synchronized (stateChanged) {
				this.state = GLGameState.Idle;
				stateChanged.notifyAll();
			}
			break;
		default:
			break;
		}
	}

	public void onSurfaceChanged(GL10 arg0, int arg1, int arg2) {
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		graphics.setGL(gl);
		
		synchronized (stateChanged) {
			if (state == GLGameState.Initialized)
				screen = getStartScreen();
			state = GLGameState.Running;
			
			//recent change was to move this into bracket
			screen.resume();
		}
		
		startTime = System.nanoTime();
	}
	
	public void setScreen(Screen screen) {
		if (screen == null)
			throw new IllegalArgumentException("Screen must not be null");
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}

	public Screen getCurrentScreen() {
		return screen;
	}

	public Screen getStartScreen() {
		// to be overridden by game.
		return null;
	}

}
