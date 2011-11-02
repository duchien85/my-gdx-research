package org.gsn.caro;

import com.badlogic.gdx.Gdx;

public class Constant {

	public static int WIDTH = 320;
	public static int HEIGHT = 240;
	public static void load(){
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
	}
	
	
}
