package org.gsn.caro;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.gsn.engine.Debug;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ImageManager {
	private static int findPower(int x) {
		int mu = (int) (Math.ceil(Math.log(x) / Math.log(2)));
		Debug.trace(" mu " + mu);
		return (int) Math.pow(2, mu);
	}

	public static TextureRegion downloadToTexture(String imageURL, int width, int height) {
		byte[] b = ImageManager.downloadImage(imageURL);
		Gdx2DPixmap map;
		try {
			map = new Gdx2DPixmap(b, 0, b.length, 0);
			Gdx2DPixmap myMap = new Gdx2DPixmap(findPower(width), findPower(height), Gdx2DPixmap.GDX2D_FORMAT_RGB888);
			for (int x = 0; x < width; x++)
				for (int y = 0; y < height; y++) {
					float fx = x;
					float fy = y;
					int nx = Math.round((fx * map.getWidth()) / width);
					int ny = Math.round((fy * map.getHeight()) / height);
					myMap.setPixel(x, y, map.getPixel(nx, ny));
				}
			Pixmap pix = new Pixmap(myMap);
			Debug.trace(pix.getWidth() + " * " + pix.getHeight());
			Texture texture = new Texture(pix);
			return new TextureRegion(texture, 0, 0, width, height);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static TextureRegion downloadToTexture(String imageURL) {
		byte[] b = ImageManager.downloadImage(imageURL);
		Gdx2DPixmap map;
		try {
			map = new Gdx2DPixmap(b, 0, b.length, 0);
			Gdx2DPixmap myMap = new Gdx2DPixmap(findPower(map.getWidth()), findPower(map.getHeight()), Gdx2DPixmap.GDX2D_FORMAT_RGB888);
			for (int x = 0; x < myMap.getWidth(); x++)
				for (int y = 0; y < myMap.getHeight(); y++) {
					if (x < map.getWidth() || y < map.getHeight())
						myMap.setPixel(x, y, map.getPixel(x, y));
					else
						myMap.setPixel(x, y, 0);
				}
			Pixmap pix = new Pixmap(myMap);
			Debug.trace(pix.getWidth() + " * " + pix.getHeight());
			Texture texture = new Texture(pix);
			return new TextureRegion(texture, 0, 0, map.getWidth(), map.getHeight());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static byte[] downloadImage(String imageURL) {
		try {
			List<Byte> list = new ArrayList<Byte>();
			URL url = new URL(imageURL);
			URLConnection ucon = url.openConnection();
			InputStream is = ucon.getInputStream();
			return downloadImage(is);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] downloadImage(InputStream in) {
		try {
			List<Byte> list = new ArrayList<Byte>();
			long startTime = System.currentTimeMillis();
			InputStream is = in;
			BufferedInputStream bis = new BufferedInputStream(is);
			byte[] b = new byte[1024];
			int current = 0;
			while ((current = bis.read(b)) != -1) {
				for (int i = 0; i < current; i++) {
					list.add(b[i]);
				}
			}
			byte[] result = new byte[list.size()];
			for (int i = 0; i < list.size(); i++) {
				result[i] = list.get(i);
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}