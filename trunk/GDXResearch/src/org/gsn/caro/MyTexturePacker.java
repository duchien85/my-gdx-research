package org.gsn.caro;

import com.badlogic.gdx.tools.imagepacker.TexturePacker;
import com.badlogic.gdx.tools.imagepacker.TexturePacker.Settings;

public class MyTexturePacker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Settings settings = new Settings();
		settings.padding = 2;
		settings.maxWidth = 1024;
		settings.maxHeight = 1024;
		settings.incremental = true;
		// new JoglApplication(new CaroGame(), "Super Jumper", Constant.WIDTH,
		// Constant.HEIGHT, false);
		TexturePacker.process(settings, "D:/libGDX/my-gdx-research/img", "D:/libGDX/my-gdx-research/MetaGunAndroid/assets/gdx");
		// Debug.trace(Gdx.files.internal("img").);
	}

}
