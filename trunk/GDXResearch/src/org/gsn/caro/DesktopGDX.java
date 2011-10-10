package org.gsn.caro;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class DesktopGDX {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		new JoglApplication(new CaroGame(), "Super Jumper", Constant.WIDTH, Constant.HEIGHT, false);
		
	}

}
