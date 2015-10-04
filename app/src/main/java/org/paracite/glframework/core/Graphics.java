package org.paracite.glframework.core;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;

public class Graphics {
	GLSurfaceView glView;
	
	public GL10 gl;
	
	public Graphics(GLSurfaceView glView) {
		this.glView = glView;
	}
	
	public void setGL(GL10 gl) {
		this.gl = gl;
	}
	
	public int getWidth() {
		return glView.getWidth();
	}
	
	public int getHeight() {
		return glView.getHeight();
	}
}
