package org.paracite.glframework;

import org.paracite.glframework.math.Rectangle;
import org.paracite.glframework.math.Vector2D;

public class GameObject {
	public final Vector2D position;
	public final Rectangle bounds;

	public GameObject(float x, float y, float width, float height) {
		this.position = new Vector2D(x, y);
		this.bounds = new Rectangle(x, y, width, height);
	}
}
