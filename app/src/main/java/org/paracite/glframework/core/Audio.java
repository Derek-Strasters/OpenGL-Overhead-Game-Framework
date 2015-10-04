package org.paracite.glframework.core;

import java.io.IOException;


import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

public class Audio {
	AssetManager mAssetManager;
	SoundPool mSoundPool;
	
	public Audio(Activity mActivity) {
		mActivity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.mAssetManager = mActivity.getAssets();
		this.mSoundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}
	
	public Music newMusic(String fileName) {
		try {
			AssetFileDescriptor mAssetDescriptor = mAssetManager.openFd(fileName);
			return new Music(mAssetDescriptor);
		}catch (IOException e) {
			throw new RuntimeException("Could not load music file \"" + fileName +"\"");
		}
	}

	public Sound newSound(String fileName) {
		try {
			AssetFileDescriptor mAssetDescriptor = mAssetManager.openFd(fileName);
			int soundId = mSoundPool.load(mAssetDescriptor, 0);
			return new Sound(mSoundPool, soundId);
		}catch (IOException e) {
			throw new RuntimeException("Could not load music file \"" + fileName +"\"");
		}
	}

	public void release(){
		mSoundPool.release();
	}
}
