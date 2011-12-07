package com.gsn.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.gsn.CaroSettings;

public class SoundManger {
	
	
	public static Music music;
	public static Sound hitSound;
	
	public static void playSound(Sound sound){
		if (CaroSettings.soundEnabled)
			sound.play();
	}

	public static void load(){
		music = Gdx.audio.newMusic(Gdx.files.internal("mdx/music.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);		
		hitSound = Gdx.audio.newSound(Gdx.files.internal("mdx/click.wav"));
	}
}
