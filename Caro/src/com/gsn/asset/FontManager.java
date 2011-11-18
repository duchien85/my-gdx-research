package com.gsn.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontManager {
	public static BitmapFont font;
	public static void load(){
		font = new BitmapFont(Gdx.files.internal("font/arial.fnt"), Gdx.files.internal("font/arial.png"), false);
	}
}
