package org.gsn;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopMain {
	public static void main (String[] args) {
        new LwjglApplication(new Game(), "Game", 480, 320, false);
}
}
