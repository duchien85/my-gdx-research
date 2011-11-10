package org.gsn.caro;

import java.io.FileInputStream;

import org.gsn.engine.Debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CaroAssets {
	static public TextureAtlas atlas;

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

//	public static Music music;
//	public static Sound hitSound;	

	public static BitmapFont font;
	public static BitmapFont arialFont;
	public static Animation bobJump;
	
	public static TextureRegion test;

	public static void load() {
		loadImage();
		loadSound();
		loadFont();
	}

	public static void loadImage() {
		String imageURL = "http://s2.gonct.info/playlist/2011/04/05/1TztueYWS0It.jpg";
		//test = ImageManager.downloadToTexture(imageURL, 32, 32);
		
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

	public static void loadFont() {
		// font
		font = new BitmapFont(Gdx.files.internal("font/font.fnt"), Gdx.files.internal("font/font.png"), false);
		arialFont = new BitmapFont(Gdx.files.internal("font/arial.fnt"), Gdx.files.internal("font/arial.png"), false);
	}

	public static void loadSound() {
		// music
//		music = Gdx.audio.newMusic(Gdx.files.internal("mdx/music.mp3"));
//		music.setLooping(true);
//		music.setVolume(0.5f);
//		hitSound = Gdx.audio.newSound(Gdx.files.internal("mdx/click.ogg"));
	}
}
