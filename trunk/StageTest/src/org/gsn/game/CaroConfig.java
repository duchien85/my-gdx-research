package org.gsn.game;

import java.io.IOException;
import java.util.Properties;

import com.badlogic.gdx.Gdx;

public class CaroConfig{
	public static String HOST;	
	private static Properties pros = new Properties();	
	public static void load(){
		try {
			pros.load(Gdx.files.internal("conf/caro.conf").read());
			HOST = pros.getProperty("host");			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
