package org.paracite.goblinking.model;

import java.util.ArrayList;
import java.util.List;

import org.paracite.glframework.math.Vector2D;

public abstract class World {
	public final int WORLD_WIDTH;
	public final int WORLD_HEIGHT;
	public final FloorWall floorsWalls[][];
	//TODO bullets and enemies least must use a pool.
	public final List<EnemySpawner> spawnerList;
	public final List<Bullet> bullets;
	public final Hero hero;
	

	public World() {
		this.WORLD_WIDTH = returnWorldWidth();
		this.WORLD_HEIGHT = returnWorldHeight();
		this.floorsWalls = returnFloorsWalls(WORLD_WIDTH,WORLD_HEIGHT);
		this.spawnerList = returnSpawnerList();
		this.bullets = new ArrayList<Bullet>();
		this.hero = new Hero(returnHeroStartPos());
	}

	
	
	public abstract int returnWorldHeight(); 
	
	public abstract int returnWorldWidth();
	
	public abstract Vector2D returnHeroStartPos();
	
	public abstract FloorWall[][] returnFloorsWalls(int width, int height);
	
	public abstract List<EnemySpawner> returnSpawnerList();
	
	//TODO update!
	public void update(float deltaTime, Controller controller) {
		hero.position.add(controller.delta);
	}
	
}
