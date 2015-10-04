package org.paracite.goblinking;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.paracite.glframework.core.Game;
import org.paracite.glframework.core.Screen;

public class GameActivity extends Game {

	//TODO needed?
	boolean firstTimeCreate = true;

	@Override
	public Screen getStartScreen() {
		return new MainMenuScreen(this);
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
	}	
}
