package org.gsn.caro;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

public class CaroGame extends Game {
	Application app;
	final static int LOBBY = 0;
	final static int BOARD = 1;
	private LobyScreen lobbyScreen;
	private BoardScreen boardScreen;
	
	public void setApp(Application app){
		this.app = app;		
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		
		CaroAssets.load();
		setScreen(LOBBY);
		if (Settings.musicEnabled)
			CaroAssets.music.play();
	}

	private void setScreen(Screen screen, InputProcessor input) {
		// TODO Auto-generated method stub
		super.setScreen(screen);
		this.app.getInput().setInputProcessor(input);
	}
	
	public void setScreen(int id){
		switch (id){
		case LOBBY:
			if (lobbyScreen == null)
				lobbyScreen = new LobyScreen(this);
			setScreen(lobbyScreen, lobbyScreen);
			break;
		case BOARD:
			if (boardScreen == null)
				boardScreen = new BoardScreen(this);
			boardScreen.clearGame();
			setScreen(boardScreen, boardScreen);
			break;
		}
	}

}
