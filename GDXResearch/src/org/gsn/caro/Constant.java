package org.gsn.caro;

import com.badlogic.gdx.Gdx;

public class Constant {

	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public static void load(){
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
	}
	
	
}
