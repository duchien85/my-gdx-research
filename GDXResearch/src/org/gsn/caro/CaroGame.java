package org.gsn.caro;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class CaroGame extends Game {
	final static int LOBBY = 0;
	final static int BOARD = 1;
	private LobyScreen lobbyScreen;
	private BoardScreen boardScreen;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		CaroAssets.load();
		setScreen(BOARD);
		if (CaroSettings.musicEnabled)
			CaroAssets.music.play();
	}
		
	public void setScreen(int id){
		switch (id){
		case LOBBY:
			if (lobbyScreen == null)
				lobbyScreen = new LobyScreen(this);
			setScreen(lobbyScreen);
			Gdx.input.setInputProcessor(lobbyScreen);
			break;
		case BOARD:
			if (boardScreen == null)
				boardScreen = new BoardScreen(this);
			boardScreen.clearGame();
			setScreen(boardScreen);
			Gdx.input.setInputProcessor(boardScreen);
			break;
		}
	}

}
