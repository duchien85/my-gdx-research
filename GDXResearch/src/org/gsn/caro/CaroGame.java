package org.gsn.caro;

import com.badlogic.gdx.Game;

public class CaroGame extends Game {

	@Override
	public void create() {
		// TODO Auto-generated method stub
		Assets.load();
		setScreen(new MainMenuScreen(this));
	}

}
