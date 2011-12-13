package com.gsn.caro;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.gsn.asset.CaroAssetManager;
import com.gsn.asset.FontManager;
import com.gsn.asset.SoundManger;
import com.gsn.screen.LobbyScreen;

public class CaroGame extends Game {
	enum ScreenType{
		LOBBY;
	}	
	private LobbyScreen lobbyScreen = new LobbyScreen();
	@Override
	public void create() {
		// TODO Auto-generated method stub
		Gdx.app.log("CaroGame", "create");
		Constant.update();
		
		FontManager.load();
		
		SoundManger.load();		
		
		CaroAssetManager.newInstance();
		CaroAssetManager.getInstance().finishLoading();
		CaroAssetManager.getInstance().assignContent();
		setScreen(ScreenType.LOBBY);
	}
	
	public void setScreen(ScreenType screen){
		switch (screen){
		case LOBBY:
			setScreen(lobbyScreen);
			Gdx.input.setInputProcessor(lobbyScreen);
			break;
		}
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
		CaroAssetManager.getInstance().finishLoading();
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
}
