package org.gsn;

import org.gsn.engine.Debug;
import org.gsn.engine.Utility;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GsnMenuSprite {
	private static int findPower(int d){
		int kq = Utility.findPower(d);
		if (kq < 128)
			kq = 128;
		return 128;
			
	}
	
	public static void scrollTextureRegion(TextureRegion tex, int amountHight){
		//Debug.trace(tex.)
		tex.setRegionY(tex.getRegionY() + amountHight);
	//	tex.setRegionHeight(tex.getRegionHeight() - amountHight );
	}
	
	public static TextureRegion createMenu(TextureRegion... arr){
		int width = 0, height = 0;
		for (int i = 0; i < arr.length; i++){
			//ImageManager.downloadToTexture("", 1, 2);
			TextureRegion tex = arr[i];
			if (width < tex.getRegionWidth())
				width = tex.getRegionHeight();
			height += tex.getRegionHeight();
		}		
		
		//Gdx2DPixmap myMap = new Gdx2DPixmap(Utility.findPower(width), Utility.findPower(height), Gdx2DPixmap.GDX2D_FORMAT_RGBA8888);			
		Gdx2DPixmap myMap = new Gdx2DPixmap(findPower(width), findPower(height), Gdx2DPixmap.GDX2D_FORMAT_RGBA8888);
		for (int i =0; i<myMap.getWidth(); i++)
			for (int j =0; j<myMap.getHeight(); j++)
				myMap.setPixel(i, j, Color.rgba8888(0f, 0f, 0f, 0f));
		Debug.trace("Format my Map: " + myMap.getFormat());
		Debug.trace("WIDTH : " + myMap.getWidth() + " H: " + myMap.getHeight());
		int mocH = 0;
		for (int i = 0; i < arr.length; i++){
			TextureRegion tex = arr[i];
			tex.getTexture().getTextureData().prepare();
			Pixmap a = tex.getTexture().getTextureData().consumePixmap();
			Debug.trace("Format Texture: " + a.getFormat());
			for (int x = 0; x < tex.getRegionWidth(); x++)
				for (int y = 0; y < tex.getRegionHeight(); y++){					
					myMap.setPixel(x, y + mocH, a.getPixel(x + (int)tex.getRegionX(), y + (int)tex.getRegionY()));
					int p = a.getPixel(x + (int)tex.getRegionX(), y + (int)tex.getRegionY());					
					
				}
			mocH += tex.getRegionHeight();
		}
		
		Pixmap pix = new Pixmap(myMap);
		Texture texture = new Texture(pix, false);
		return new TextureRegion(texture, 0, 0, width, height);
		
		//return new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
	}
}
