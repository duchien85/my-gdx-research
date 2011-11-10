package org.badlogic;

import org.gsn.caro.CaroGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.android.AndroidApplication;

public class MetaGunAndroidActivity extends AndroidApplication {
	public static Game game;
	public void onCreate(android.os.Bundle savedInstanceState) {
		if (game == null)
			game = new CaroGame();
		super.onCreate(savedInstanceState);
		initialize(game, false);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		game.dispose();
		game = null;
		super.onBackPressed();
	}
}