package org.paracite.goblinking.model;

import org.paracite.glframework.GameObject;
import org.paracite.glframework.math.Vector2D;

public class Hero extends GameObject {
	public static final float HERO_WIDTH  = 0.3f;
	public static final float HERO_HEIGHT = 0.3f; 
	

	public Hero(Vector2D start) {
		super(start.x, start.y, HERO_WIDTH, HERO_HEIGHT);
		// TODO Auto-generated constructor stub
	}

}
