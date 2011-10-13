package org.gsn.caro;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.gsn.engine.Debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.jogl.JoglApplication;
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
		new JoglApplication(new CaroGame(), "Super Jumper", Constant.WIDTH, Constant.HEIGHT, false);		
		String imageURL = "http://s2.gonct.info/playlist/2011/04/05/1TztueYWS0It.jpg";
//		byte[] b = ImageManager.download(imageURL);
//		Pixmap pix = new Pixmap(b, 0, b.length);
//		Debug.trace(b.length);		
//		byte[] b = ImageManager.download(new FileInputStream("C:/Documents and Settings/trungdv2/a.png"));
//		
//		
//		Gdx2DPixmap map = new Gdx2DPixmap(b, 0, b.length, 0);
//		Gdx2DPixmap myMap = new Gdx2DPixmap(256, 256, 0);
//		for (int x = 0; x < myMap.getWidth(); x++)
//			for (int y = 0; y < myMap.getHeight(); y++){
//				if (x <= map.getWidth() || y <= map.getHeight())
//					myMap.setPixel(x, y, map.getPixel(x, y) );
//			}
//		Pixmap pix = new Pixmap(myMap);		
//		Debug.trace(pix.getWidth() + " * " + pix.getHeight());
//		texture = new Texture(pix);
	}
}

