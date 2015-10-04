package org.paracite.glframework;

import javax.microedition.khronos.opengles.GL10;

import org.paracite.glframework.core.Graphics;
import org.paracite.glframework.math.Vector2D;

public class Camera2D {
	public final float frustumWidth;
	public final float frustumHeight;
	
	private final float widthNorm;
	private final float heightNorm;

	public final Vector2D position;
	public float zoom;
	
	Graphics graphics;

	public Camera2D(Graphics graphics, float frustumWidth, float frustumHeight) {
		this.graphics = graphics;
		this.frustumWidth = frustumWidth;
		this.frustumHeight = frustumHeight;
		this.widthNorm = frustumWidth / (float) graphics.getWidth();
		this.heightNorm = frustumHeight / (float) graphics.getHeight();
		this.position = new Vector2D(frustumWidth / 2, frustumHeight / 2);
		this.zoom = 1.0f;
	}

	public void setViewportAndMatrices() {
		GL10 gl = graphics.gl;
		
		float widthFactor = frustumWidth * zoom / 2;
		float heightFactor = frustumHeight * zoom / 2;
		
		gl.glViewport(0, 0, graphics.getWidth(), graphics.getHeight());
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(position.x - widthFactor, 
					position.x + widthFactor, 
					position.y - heightFactor, 
					position.y + heightFactor, 
					1, -1);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	public void setViewport() {
		GL10 gl = graphics.gl;
		
		gl.glViewport(0, 0, graphics.getWidth(), graphics.getHeight());
	}
	
	public void setMatrices() {
		GL10 gl = graphics.gl;
		
		float widthFactor = frustumWidth * zoom / 2;
		float heightFactor = frustumHeight * zoom / 2;
		
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(position.x - widthFactor, 
					position.x + widthFactor, 
					position.y - heightFactor, 
					position.y + heightFactor, 
					1, -1);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	public void touchToWorld(Vector2D touch) {
		touch.x = touch.x * widthNorm;
		touch.y = frustumHeight - touch.y * heightNorm;
		touch.add(position).subtract(frustumWidth * zoom / 2, frustumHeight * zoom / 2);
		}
	
	public void touchNormalize(Vector2D touch) {
		touch.x = touch.x * widthNorm;
		touch.y = frustumHeight - touch.y * heightNorm;
	}
}
