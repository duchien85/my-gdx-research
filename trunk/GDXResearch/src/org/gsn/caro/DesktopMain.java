package org.gsn.caro;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopMain {
	public static void main (String[] args) {
        new LwjglApplication(new CaroGame(), "Game", 320, 480, false);		
	}
}
