package org.paracite.goblinking;

import javax.microedition.khronos.opengles.GL10;

import org.paracite.glframework.FPSCounter;
import org.paracite.glframework.core.Game;
import org.paracite.glframework.core.Screen;
import org.paracite.goblinking.levels.Level001;
import org.paracite.goblinking.model.Controller;
import org.paracite.goblinking.model.World;
import org.paracite.goblinking.model.WorldRenderer;

public class Level001GameScreen extends Screen {
	private final World world;
	private final WorldRenderer renderer; 
	private final Controller controller;
	
	FPSCounter fps;
	
	//TODO do we need separate screens for each world?
	public Level001GameScreen(Game game) {
		super(game);

		this.world = new Level001();
		this.renderer = new WorldRenderer(game, world, "level001textures.png");
		this.controller = new Controller(renderer.cam);
		
		fps = new FPSCounter(3);
	}

	@Override
	public void update(float deltaTime) {
		if (deltaTime > 0.1f)
			deltaTime = 0.1f;
		controller.update(game.input.getTouchEvents(),game.input.getKeyEvents());
		world.update(deltaTime, controller);
	}

	@Override
	public void present(float deltaTime) {
		renderer.render();
		
		fps.logFrame(deltaTime);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
		graphics.gl.glEnable(GL10.GL_TEXTURE_2D);
		renderer.resume();
	}

	@Override
	public void dispose() {
		renderer.dispose();
	}
}
