package org.gsn.game;

import org.gsn.TestGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopGDX {	
	public static void main(String[] args) {	
		new LwjglApplication(new CaroGame(null), "My Caro", Constant.WIDTH, Constant.HEIGHT, false);		
	}
}
