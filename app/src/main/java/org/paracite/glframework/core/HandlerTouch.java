package org.paracite.glframework.core;

import java.util.List;

import org.paracite.glframework.core.Input.TouchEvent;

import android.view.View.OnTouchListener;


public interface HandlerTouch extends OnTouchListener {
	public boolean isTouchDown(int pointer);

	public int getTouchX(int pointer);

	public int getTouchY(int pointer);

	public List<TouchEvent> getTouchEvents();
}
