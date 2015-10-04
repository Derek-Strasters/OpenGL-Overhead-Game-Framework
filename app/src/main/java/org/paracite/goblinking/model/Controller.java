package org.paracite.goblinking.model;

import java.util.List;

import org.paracite.glframework.Camera2D;
import org.paracite.glframework.core.Input.KeyEvent;
import org.paracite.glframework.core.Input.TouchEvent;
import org.paracite.glframework.math.Vector2D;

public class Controller {
	public Vector2D delta;

	static final Vector2D zero = new Vector2D(0,0);
	Vector2D touchPos;
	Vector2D lastPos;
	Camera2D cam;

	public Controller(Camera2D cam) {
		this.touchPos = new Vector2D(0, 0);
		this.lastPos = new Vector2D(0, 0);
		this.delta = new Vector2D(0, 0);
		this.cam = cam;
	}

	public void update(List<TouchEvent> touchEvents, List<KeyEvent> keyEvents) {
		int touchSize = touchEvents.size();
		
		if (delta != zero) 
			delta.set(zero);

		if (touchSize == 0)
			return;

		TouchEvent touchEvent = touchEvents.get(0);

		if (touchEvent.type == TouchEvent.TOUCH_DOWN) {
			touchPos.x = touchEvent.x;
			touchPos.y = touchEvent.y;
			cam.touchNormalize(touchPos);
			lastPos.set(touchPos);
		}
	 
		touchEvent = touchEvents.get(touchSize - 1);
		touchPos.x = touchEvent.x;
		touchPos.y = touchEvent.y;
		cam.touchNormalize(touchPos);

		if (touchEvent.type == TouchEvent.TOUCH_DOWN) {
			lastPos.set(touchPos);
			return;
		} else if (touchEvent.type == TouchEvent.TOUCH_DRAGGED) {
			delta.set(lastPos);
			delta.subtract(touchPos);
			lastPos.set(touchPos);
			return;
		} 
	}
}
