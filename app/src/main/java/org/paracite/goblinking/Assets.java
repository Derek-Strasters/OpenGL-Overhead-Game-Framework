package org.paracite.goblinking;

import org.paracite.glframework.Texture;
import org.paracite.glframework.TextureRegion;
import org.paracite.glframework.core.Sound;

public class Assets {
	public static Texture mainMenu;
	public static TextureRegion mainMenuRegion;
	
	public static void playSound(Sound sound) {
		if (Settings.soundEnabled)
			sound.play(1);
	}
}
