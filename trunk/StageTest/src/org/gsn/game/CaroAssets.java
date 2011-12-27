package org.gsn.game;

import java.util.List;

import org.gsn.engine.Debug;
import org.gsn.engine.GSNAnimation;
import org.gsn.engine.GsnTextureAtlas;
import org.gsn.engine.GsnTextureAtlas.AtlasRegion;
import org.gsn.engine.loading.ILoadingListenner;
import org.gsn.engine.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CaroAssets {
	public  Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}
	private static CaroAssetManager _instance = null;
	public static CaroAssetManager getInstance() {
		return _instance;
	}
	public static CaroAssetManager newInstance() {
		_instance = new CaroAssetManager();
		return _instance;
	}
	public enum SoundType{
		HIT, WIN, LOSE,CLICK, TIME
	}
	public  void playSoundFile(Sound sound){
		if (CaroSettings.soundEnabled)
			sound.play();
	}
	
}
