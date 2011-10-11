package org.gsn.caro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class CaroAssets {
	static private TextureAtlas atlas;

	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static Sprite board;
	public static Sprite pieceO;
	public static Sprite pieceX;
	public static Sprite ready;
	public static Sprite win;
	public static Sprite lose;
	public static Sprite back;
	public static Sprite cuoc1G;
	public static Sprite background;
	public static Sprite waitOpponent;
	public static Sprite avatar;
	
	public static Music music;
	public static Sound hitSound;

	public static BitmapFont font;
	public static Animation bobJump;

	public static void load(){
		loadImage();
		loadSound();
		loadFont();
	}
	
	public static void loadImage() {
		// texture
		atlas = new TextureAtlas(Gdx.files.internal("gdx/pack"));
		background = atlas.createSprite("background");
		avatar = atlas.createSprite("avatar");
		cuoc1G = atlas.createSprite("cuoc1G");
		board = atlas.createSprite("board");
		pieceO = atlas.createSprite("dauO");
		pieceX = atlas.createSprite("dauX");
		ready = atlas.createSprite("ready");
		win = atlas.createSprite("win");
		lose = atlas.createSprite("lose");
		back = atlas.createSprite("back");
		waitOpponent = atlas.createSprite("wait");				
	}
	
	public static void loadFont(){
		// font
		font = new BitmapFont(Gdx.files.internal("font/font.fnt"), Gdx.files.internal("font/font.png"), false);
	}
	public static void loadSound(){
		// music
		music = Gdx.audio.newMusic(Gdx.files.internal("mdx/music.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);
		hitSound = Gdx.audio.newSound(Gdx.files.internal("mdx/click.ogg"));
	}
}
