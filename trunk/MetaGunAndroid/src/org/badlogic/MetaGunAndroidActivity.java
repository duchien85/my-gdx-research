package org.badlogic;

import org.gsn.Ping;
import org.gsn.Ping.IPingListener;
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
	protected void onResume() {
		// TODO Auto-generated method stub		
		super.onResume();
	}
	
	@Override
	public void finish(){
		// TODO Auto-generated method stub
		game.dispose();
		game = null;
		super.finish();
	}
}