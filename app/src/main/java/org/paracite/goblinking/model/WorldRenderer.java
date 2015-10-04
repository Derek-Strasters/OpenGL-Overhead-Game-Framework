package org.paracite.goblinking.model;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import org.paracite.glframework.Camera2D;
import org.paracite.glframework.SpriteBatcher;
import org.paracite.glframework.Texture;
import org.paracite.glframework.TextureRegion;
import org.paracite.glframework.core.Game;
import org.paracite.goblinking.model.World;

public class WorldRenderer {
	static final float FRUSTUM_WIDTH = 4.0f;
	static final float FRUSTUM_HEIGHT = 3.0f;
	static TextureRegion[] regions = new TextureRegion[48];
	
	public SpriteBatcher batcher;
	public Game game;
	public Texture levelTexture;
	public Camera2D cam;
	
	World world;
	
	public WorldRenderer(Game game, World world,String resourceName) {
		this.game = game;
		this.world = world;
		this.levelTexture = loadTexture(resourceName);
		this.batcher = new SpriteBatcher(game.graphics, 256);
		
		loadTextureRegions();
		
		this.cam = new Camera2D(game.graphics, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		
		//TODO temporary camera control
		cam.position.set(world.hero.position);
	}
	
	public Texture loadTexture(String resourceName) {
		return new Texture(game, resourceName);
	}
	
	public void drawFloorWall(FloorWall floorWall) {
		batcher.drawSprite(floorWall.position.x, floorWall.position.y, FloorWall.WALL_WIDTH, FloorWall.WALL_HEIGHT, regions[floorWall.type]);
	}
	
	public void drawSpawner(EnemySpawner spawner) {
		//TODO render spawner code here.
	}
	
	public void resume() {
		levelTexture.reload();
		
		//TODO make this work? (optional): cam.setViewport(); 
	}
	
	public void dispose() {
		levelTexture.dispose();
	}
	
	public void render() {
		//TODO real camera controls here
		cam.position.set(world.hero.position);		
		
		cam.setViewportAndMatrices();
		
		//TODO clear will not be needed eventually
		game.graphics.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//TODO renderForeground();
		renderBackground();	
	}

	private void renderBackground() {
		FloorWall[][] floorsWalls = world.floorsWalls;	
		List<EnemySpawner> spawnerList = world.spawnerList;
		
		batcher.beginBatch(levelTexture);
		
		for (int i = 0; i < floorsWalls.length; i++) {
			for (int j = 0; j < floorsWalls[i].length; j++) {
				FloorWall wall = floorsWalls[i][j];
				drawFloorWall(wall);
			}
		}
		
		for (int i = 0; i < spawnerList.size(); i++) {
			EnemySpawner spawner = spawnerList.get(i);
			drawSpawner(spawner);
		}
		
		batcher.endBatch();
		
		//TODO second batch run for GUI and foreground items.
	}
	
	public void loadTextureRegions() {
		regions[0] 	= new TextureRegion(levelTexture, 000, 000, 128, 128, 1);
		regions[1]	= new TextureRegion(levelTexture, 000, 000, 128, 128, 0);
		regions[2] 	= new TextureRegion(levelTexture, 256, 000, 128, 128, 0);
		regions[3] 	= new TextureRegion(levelTexture, 256, 000, 128, 128, 2);
		regions[4] 	= new TextureRegion(levelTexture, 256, 000, 128, 128, 3);
		regions[5] 	= new TextureRegion(levelTexture, 256, 000, 128, 128, 1);
		regions[6] 	= new TextureRegion(levelTexture, 128, 000, 128, 128, 0);
		regions[7] 	= new TextureRegion(levelTexture, 128, 000, 128, 128, 3);
		regions[8] 	= new TextureRegion(levelTexture, 128, 000, 128, 128, 2);
		regions[9]	= new TextureRegion(levelTexture, 128, 000, 128, 128, 1);
		regions[10]	= new TextureRegion(levelTexture, 384, 000, 128, 128, 0);
		regions[11]	= new TextureRegion(levelTexture, 384, 000, 128, 128, 1);
		regions[12]	= new TextureRegion(levelTexture, 384, 000, 128, 128, 2);
		regions[13]	= new TextureRegion(levelTexture, 384, 000, 128, 128, 3);
		regions[14]	= new TextureRegion(levelTexture, 512, 000, 128, 128, 0);
		
		regions[15] = new TextureRegion(levelTexture, 384, 128, 128, 128, 0);
	}
}
