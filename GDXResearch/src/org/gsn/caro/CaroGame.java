package org.gsn.caro;

import com.badlogic.gdx.Game;

public class CaroGame extends Game {

	@Override
	public void create() {
		// TODO Auto-generated method stub
		CaroAssets.load();
		setScreen(new BoardScreen());
	}

}
