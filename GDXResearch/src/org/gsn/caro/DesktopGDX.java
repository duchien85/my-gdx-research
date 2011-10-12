package org.gsn.caro;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import com.badlogic.gdx.backends.jogl.JoglApplication;


public class DesktopGDX {
	public static void downloadImage(String path) throws Exception { 
		URL url = new URL(path); 
		BufferedImage bi = ImageIO.read(url); 
		File f = new File("D:/main.jpg"); 
		ImageIO.write(bi,"jpg", f);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception  {
		// TODO Auto-generated method stub
		new JoglApplication(new CaroGame(), "Super Jumper", Constant.WIDTH, Constant.HEIGHT, false);
	
//		String imageURL = "http://s2.gonct.info/playlist/2011/04/05/1TztueYWS0It.jpg";
//		ImageManager.DownloadFromUrl(imageURL, "D:/a.jpg");
		
	}

}
