package org.paracite.glframework.math;

public class Vector2D {
	public static final float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;
	public static final float TO_DEGREES = (1 / (float) Math.PI) * 180;
	public float x, y;

	public Vector2D() {
	}

	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D copy() {
		return new Vector2D(x, y);
	}

	public Vector2D set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Vector2D set(Vector2D other) {
		this.x = other.x;
		this.y = other.y;
		return this;
	}

	public Vector2D add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector2D add(Vector2D other) {
		this.x += other.x;
		this.y += other.y;
		return this;
	}

	public Vector2D subtract(float x, float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	public Vector2D subtract(Vector2D other) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}

	public Vector2D multiply(float scalar) {
		this.x *= scalar;
		this.y *= scalar;
		return this;
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y);
	}

	public Vector2D negate() {
		x = -x;
		y = -y;
		return this;
	}

	public Vector2D normalize() {
		float len = length();
		if (len != 0) {
			this.x /= len;
			this.y /= len;
		}
		return this;
	}

	public float angle() {
		float angle = (float) Math.atan2(y, x) * TO_DEGREES;
		if (angle < 0)
			angle += 360;
		return angle;
	}

	public Vector2D rotate(float angle) {
		float rad = angle * TO_RADIANS;
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);
		float newX = this.x * cos - this.y * sin;
		float newY = this.x * sin + this.y * cos;
		this.x = newX;
		this.y = newY;
		return this;
	}

	public float distanceTo(Vector2D other) {
		float distX = this.x - other.x;
		float distY = this.y - other.y;
		return (float) Math.sqrt(distX * distX + distY * distY);
	}

	public float distanceTo(float x, float y) {
		float distX = this.x - x;
		float distY = this.y - y;
		return (float) Math.sqrt(distX * distX + distY * distY);
	}

	public float distSquared(float x, float y) {
		float distX = this.x - x;
		float distY = this.y - y;
		return distX * distX + distY * distY;
	}

	public float distSquared(Vector2D other) {
		float distX = this.x - other.x;
		float distY = this.y - other.y;
		return distX * distX + distY * distY;
	}
}
