package org.paracite.glframework.core;

import android.media.SoundPool;

public class Sound {
	int soundId;
	SoundPool mSoundPool;
	
	Sound(SoundPool mSoundPool, int soundId) {
		this.soundId = soundId;
		this.mSoundPool = mSoundPool;
	}
	
	public void play(float volume) {
		mSoundPool.play(soundId, volume, volume, 0, 0, 1);
	}
	
	public void play(float volume, float pitch) {
		mSoundPool.play(soundId, volume, volume, 0, 0, pitch);
	}
	
	public void play(float volumeLeft, float volumeRight, float pitch) {
		mSoundPool.play(soundId, volumeLeft, volumeRight, 0, 0, pitch);		
	}

	public void dispose() {
		mSoundPool.unload(soundId);
	}

}
