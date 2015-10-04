package org.paracite.glframework.math;

public class Rectangle {
	public final Vector2D center;
	public float width, height;
	public float halfWidth, halfHeight;

	public Rectangle(float x, float y, float width, float height) {
		this.center = new Vector2D(x, y);
		this.width = width;
		this.height = height;
		this.halfWidth = width / 2;
		this.halfHeight = height / 2;
	}
	
	public void resize(float width, float height) {
		this.width = width;
		this.height = height;
		this.halfWidth = width / 2;
		this.halfHeight = height / 2;
	}
}
