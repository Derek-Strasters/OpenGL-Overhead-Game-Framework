package org.paracite.goblinking.model;

import org.paracite.glframework.GameObject;

public class FloorWall extends GameObject {
	public static final float WALL_WIDTH = 1.0f;
	public static final float WALL_HEIGHT = 1.0f;
	
	public static final int H   =  0;
	public static final int V   =  1;
	public static final int RT  =  2;
	public static final int LT  =  3;
	public static final int DT  =  4;
	public static final int UT  =  5;
	public static final int DRC =  6;
	public static final int LDC =  7;
	public static final int ULC =  8;
	public static final int RUC =  9;
	public static final int UE  = 10;
	public static final int LE  = 11;
	public static final int DE  = 12;
	public static final int RE  = 13;
	public static final int C   = 14;
	
	public static final int F   = 15;

	public int type;
	
	public FloorWall(float x, float y, int type) {
		super(x, y, WALL_WIDTH, WALL_HEIGHT);
		this.type = type;
	}
	
	//TODO check bullet collisions
	//TODO check ground collisions

}
