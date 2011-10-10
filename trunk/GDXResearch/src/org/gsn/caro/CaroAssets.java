package org.gsn.caro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CaroAssets {
	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}	
	
	public static Texture background;
	public static TextureRegion backgroundRegion;
	
	public static Texture items;
	public static TextureRegion all;
	public static TextureRegion board;
	public static TextureRegion pieceO;
	public static TextureRegion pieceX;
	public static TextureRegion ready;
	public static TextureRegion win;
	public static TextureRegion lose;
	public static TextureRegion back;
	public static TextureRegion cuoc1G;
	public static Music music;
	public static Sound hitSound;
	
	public static BitmapFont font;
	public static Animation bobJump;
	
	public static void load() {
		items = loadTexture("data/caro.png");
		
		all = new TextureRegion(items, 0, 0, 512, 512);		
		board = new TextureRegion(items, 0, 0, 320, 320);
		pieceO = new TextureRegion(items, 0, 492, 20, 20);
		pieceX = new TextureRegion(items, 20, 492, 20, 20);
		ready = new TextureRegion(items, 0, 320, 280, 80);
		win = new TextureRegion(items, 320, 0, 100, 115);
		lose = new TextureRegion(items, 320, 0, 100, 115);
		cuoc1G = new TextureRegion(items, 320, 0, 100, 115);
		back = new TextureRegion(items, 0, 400, 32, 32);

		background = loadTexture("data/background.png");
		backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
		//music
		music = Gdx.audio.newMusic(Gdx.files.internal("data/music.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);		
		hitSound = Gdx.audio.newSound(Gdx.files.internal("data/click.ogg"));
		bobJump = new Animation(0.2f, new TextureRegion(items, 0, 128, 32, 32), new TextureRegion(items, 32, 128, 32, 32));
		//font
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);			
	}
}
