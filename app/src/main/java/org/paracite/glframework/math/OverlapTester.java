package org.paracite.glframework.math;

public class OverlapTester {
	public static boolean overlapCircles(Circle c1, Circle c2) {
		float distance = c1.center.distSquared(c2.center);
		float radiusSum = c1.radius + c2.radius;
		return distance <= radiusSum * radiusSum;
	}

	public static boolean overlapRectangles(Rectangle r1, Rectangle r2) {
		if (
				Math.abs(r1.center.x - r2.center.x) < r1.halfWidth + r2.halfWidth && 
				Math.abs(r1.center.y - r2.center.y) < r1.halfHeight + r2.halfHeight)
			return true;
		else
			return false;
	}

	public static boolean overlapCircleRectangle(Circle c, Rectangle r) {
		float closestX = c.center.x;
		float closestY = c.center.y;
		if (c.center.x < r.center.x - r.halfWidth) {
			closestX = r.center.x - r.halfWidth;
		} else if (c.center.x > r.center.x + r.halfWidth) {
			closestX = r.center.x + r.halfWidth;
		}
		if (c.center.y < r.center.y - r.halfHeight) {
			closestY = r.center.y - r.halfHeight;
		} else if (c.center.y > r.center.y + r.halfHeight) {
			closestY = r.center.y + r.halfHeight;
		}
		return c.center.distSquared(closestX, closestY) < c.radius * c.radius;
	}

	public static boolean pointInCircle(Circle c, Vector2D p) {
		return c.center.distSquared(p) < c.radius * c.radius;
	}

	public static boolean pointInCircle(Circle c, float x, float y) {
		return c.center.distSquared(x, y) < c.radius * c.radius;
	}

	public static boolean pointInRectangle(Rectangle r, Vector2D p) {
		return  r.center.x - r.halfWidth <= p.x && 
				r.center.x + r.halfWidth >= p.x && 
				r.center.y - r.halfHeight <= p.y && 
				r.center.y + r.halfHeight >= p.y;
	}

	public static boolean pointInRectangle(Rectangle r, float x, float y) {
		return  r.center.x - r.halfWidth <= x && 
				r.center.x + r.halfWidth >= x && 
				r.center.y - r.halfHeight <= y && 
				r.center.y + r.halfHeight >= y;
	}
}