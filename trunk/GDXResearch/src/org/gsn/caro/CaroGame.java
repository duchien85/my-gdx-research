package org.gsn.caro;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

public class CaroGame extends Game {
	Application app;
	
	public void setApp(Application app){
		this.app = app;
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		
		CaroAssets.load();
		BoardScreen boardScreen = new BoardScreen();
		setScreen(boardScreen, boardScreen);		
	}

	public void setScreen(Screen screen, InputProcessor input) {
		// TODO Auto-generated method stub
		super.setScreen(screen);
		this.app.getInput().setInputProcessor(input);
	}

}
