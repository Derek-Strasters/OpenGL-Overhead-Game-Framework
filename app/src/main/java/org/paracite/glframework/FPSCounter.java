package org.paracite.glframework;

import android.util.Log;

public class FPSCounter {
	final float TICK_TIME;
	float tickTimer = 0;
	int frames = 0;

	public FPSCounter(float tickTime) {
		this.TICK_TIME = tickTime;
	}

	public void logFrame(float deltaTime) {
		tickTimer += deltaTime;
		frames++;

		if (tickTimer > TICK_TIME) {
			float fps = frames / tickTimer;
			Log.d("FPSCounter", "fps: " + fps);
			
			frames = 0;
			tickTimer = 0;
		}
	}
}
