package org.gsn;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.imagepacker.TexturePacker;
import com.badlogic.gdx.tools.imagepacker.TexturePacker.Settings;

public class MyTexturePacker {

	/**
	 * @param args
	 *            TexturePacker.process(settings,
	 *            "D:/Work/android/caro/CaroContent",
	 *            "D:/Work/android/caro/my-gdx-research/MetaGunAndroid/assets/gdx"
	 *            );
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Settings settings = new Settings();
		settings.padding = 2;
		settings.maxWidth = 1024;
		settings.maxHeight = 1024;
		settings.defaultFilterMin = TextureFilter.Linear;
		settings.defaultFilterMag = TextureFilter.Linear;
		
		// new JoglApplication(new CaroGame(), "Super Jumper", Constant.WIDTH,
		// Constant.HEIGHT, false);
		//TexturePacker.process(settings, "D:/Longvh/Projects/android/caro/Loading", "D:/Longvh/Projects/android/caro/my-gdx-research/MetaGunAndroid/assets/gdx");
		TexturePacker.process(settings, "D:/Longvh/Eclipse/CaroContent320480", "D:/Longvh/Eclipse/my-gdx-research/MetaGunAndroid2/assets/gdx/320480");
		System.out.println("end");		
		// Debug.trace(Gdx.files.internal("img").);
	}
}