package org.paracite.glframework.core;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class Music implements OnCompletionListener {
	MediaPlayer mMediaPlayer;
	boolean isPrepared = false;	
	
	public Music(AssetFileDescriptor mAssetFileDescriptor) {
		mMediaPlayer = new MediaPlayer();
		try {
			mMediaPlayer.setDataSource(mAssetFileDescriptor.getFileDescriptor(),
					mAssetFileDescriptor.getStartOffset(),
					mAssetFileDescriptor.getLength());
			mMediaPlayer.prepare();
			isPrepared = true;
			mMediaPlayer.setOnCompletionListener(this);
		}catch (Exception e) {
			throw new RuntimeException("Error loading music file");
		}
	}
	
	public void play() {
		if (mMediaPlayer.isPlaying())
			return;
		
		try {
			synchronized (this) {
				if (!isPrepared) {
					mMediaPlayer.prepare();
					isPrepared = true;
				}
				mMediaPlayer.start();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		mMediaPlayer.stop();
		synchronized (this) {
			isPrepared = false;
		}
	}

	public void pause() {
		mMediaPlayer.pause();
	}

	public void setLooping(boolean isLooping) {
		mMediaPlayer.setLooping(isLooping);
	}

	public void setVolume(float volume) {
		mMediaPlayer.setVolume(volume, volume);
	}

	public boolean isPlaying() {
		return mMediaPlayer.isPlaying();
	}

	public boolean isStopped() {
		return !isPrepared;
	}

	public boolean isLooping() {
		return mMediaPlayer.isLooping();
	}

	public void dispose() {
		if (mMediaPlayer.isPlaying())
			mMediaPlayer.stop();
		mMediaPlayer.release();
	}
	
	public void onCompletion(MediaPlayer mMediaPlayer) {
		synchronized (this) {
			isPrepared = false;
		}
	}

}
