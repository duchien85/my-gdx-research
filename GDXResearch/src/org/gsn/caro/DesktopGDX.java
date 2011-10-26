package org.gsn.caro;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.gsn.engine.Debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;


public class DesktopGDX {
	public static void downloadImage(String path) throws Exception { 
		URL url = new URL(path); 
		BufferedImage bi = ImageIO.read(url); 
		File f = new File("D:/main.jpg"); 
		ImageIO.write(bi,"jpg", f);
	}
	
	public static Texture texture;
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception  {
		// TODO Auto-generated method stub
		//new JoglApplication(new CaroGame(), "Super Jumper", Constant.WIDTH, Constant.HEIGHT, false);
		new LwjglApplication(new CaroGame(), "Super Jumper", Constant.WIDTH, Constant.HEIGHT, false);				
		
//		String url = "http://myplay.apps.zing.vn/caro/service.php?act=getServerInfo";
//		byte[] b = ImageManager.downloadImage(url);
//		Debug.trace(new String(b));
	}
}
