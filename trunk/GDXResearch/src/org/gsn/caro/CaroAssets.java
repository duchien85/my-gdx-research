package org.gsn.caro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CaroAssets {
	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	public static Texture items;
	public static TextureRegion board;
	public static TextureRegion pieceO;
	public static TextureRegion pieceX;
	public static TextureRegion ready;
	public static TextureRegion win;
	public static void load () {
		items = loadTexture("data/caro.png");
		board = new TextureRegion(items, 0, 0, 320, 320);
		pieceO = new TextureRegion(items, 0, 492, 20, 20);
		pieceX = new TextureRegion(items, 20, 492, 20, 20);
		ready = new TextureRegion(items, 0, 320, 280, 80);
		win = new TextureRegion(items, 320, 0, 100, 115);
	}
}
