package org.paracite.goblinking;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import org.paracite.glframework.Camera2D;
import org.paracite.glframework.SpriteBatcher;
import org.paracite.glframework.Texture;
import org.paracite.glframework.TextureRegion;
import org.paracite.glframework.core.Game;
import org.paracite.glframework.core.Screen;
import org.paracite.glframework.core.Input.TouchEvent;
import org.paracite.glframework.math.OverlapTester;
import org.paracite.glframework.math.Rectangle;
import org.paracite.glframework.math.Vector2D;

public class MainMenuScreen extends Screen {
	private Camera2D guiCam;
	private Rectangle playBounds;
	private SpriteBatcher batcher;
	private Vector2D touchPoint;

	public MainMenuScreen(Game game) {
		super(game);
		guiCam = new Camera2D(graphics, 800, 480);
		playBounds = new Rectangle(681, 64, 268, 128);
		touchPoint = new Vector2D();
		batcher = new SpriteBatcher(graphics, 1);
		
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.input.getTouchEvents();
		game.input.getKeyEvents();
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			touchPoint.set(event.x, event.y);
			guiCam.touchToWorld(touchPoint);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (OverlapTester.pointInRectangle(playBounds, touchPoint)) {
					
					game.setScreen(new Level001GameScreen(game));
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = graphics.gl;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setMatrices();
		gl.glEnable(GL10.GL_TEXTURE_2D);
		batcher.beginBatch(Assets.mainMenu);
		batcher.drawSprite(400, 240, 800, 480, Assets.mainMenuRegion);
		batcher.endBatch();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
		Assets.mainMenu = new Texture(game, "mainmenu.png");
		Assets.mainMenuRegion = new TextureRegion(Assets.mainMenu, 0, 0, 800, 480);
		
		guiCam.setViewport();
	}

	@Override
	public void dispose() {
		Assets.mainMenu.dispose();
	}
}
