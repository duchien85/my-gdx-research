package com.gsn;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class Desktop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JoglApplication(new CaroGame(), "Caro", Constant.WIDTH, Constant.HEIGHT, false);
	}

}
