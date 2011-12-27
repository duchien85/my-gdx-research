package org.gsn;



import org.gsn.game.CaroGame;
import org.gsn.game.Constant;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class TestGame {
	public static int cong(int i){
		if (i==1) return 1;
		else if (i==2) return 2;
		else return (int) (3*Math.pow(2, i-3));
	}
	public static void main(String[] args) {	
		new LwjglApplication(new CaroGame(null), "My Caro", 480, 320, false);		
	}
}
