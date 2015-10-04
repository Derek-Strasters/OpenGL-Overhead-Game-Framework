package org.paracite.glframework;

public class Animation {
	public static final int LOOPING = 0;
	public static final int NONLOOPING = 1;
	final TextureRegion[] keyFrames;
	final float frameDuration;

	public Animation(float frameDuration, TextureRegion... keyFrames) {
		this.frameDuration = frameDuration;
		this.keyFrames = keyFrames;
	}

	public TextureRegion getKeyFrame(float stateTime, int mode) {
		int frameNumber = (int) (stateTime / frameDuration);

		if (mode == NONLOOPING) {
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);
		} else {
			frameNumber = frameNumber % keyFrames.length;
		}
		return keyFrames[frameNumber];
	}
}
