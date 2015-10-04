package org.paracite.glframework.core;

import java.util.List;


import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

public class Input {
	public static class KeyEvent {
		public static final int KEY_DOWN = 0;
		public static final int KEY_UP = 1;

		public int type;
		public int keyCode;
		public char keyChar;
	}

	public static class TouchEvent {
		public static final int TOUCH_DOWN = 0;
		public static final int TOUCH_UP = 1;
		public static final int TOUCH_DRAGGED = 2;

		public int type;
		public int x, y;
		public int pointer;
	}
	
	HandlerAccelerometer mAccelHandler;
	HandlerKeyboard mKeyHandler;
	HandlerTouch mTouchHandler;
	
	public Input(Context context, View view, float scaleX, float scaleY) {
		mAccelHandler = new HandlerAccelerometer(context);
		mKeyHandler = new HandlerKeyboard(view);
		if (VERSION.SDK_INT < 5)
			mTouchHandler = new HandlerSingleTouch(view, scaleX, scaleY);
		else
			mTouchHandler = new HandlerMultiTouch(view, scaleX, scaleY);
	}
	
	public boolean isKeyPressed(int keyCode) {
		return mKeyHandler.isKeyPressed(keyCode);
	}

	public boolean isTouchDown(int pointer) {
		return mTouchHandler.isTouchDown(pointer);
	}

	public int getTouchX(int pointer) {
		return mTouchHandler.getTouchX(pointer);
	}

	public int getTouchY(int pointer) {
		return mTouchHandler.getTouchY(pointer);
	}

	public float getAccelX() {
		return mAccelHandler.getAccelX();
	}

	public float getAccelY() {
		return mAccelHandler.getAccelY();
	}

	public float getAccelZ() {
		return mAccelHandler.getAccelZ();
	}

	public List<KeyEvent> getKeyEvents() {
		return mKeyHandler.getKeyEvents();
	}

	public List<TouchEvent> getTouchEvents() {
		return mTouchHandler.getTouchEvents();
	}

}
