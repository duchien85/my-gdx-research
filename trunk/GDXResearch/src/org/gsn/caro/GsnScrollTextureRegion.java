package org.gsn.caro;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GsnScrollTextureRegion extends TextureRegion {
	public GsnScrollTextureRegion (Texture texture, int x, int y, int width, int height) {
		super(texture, x, y, width, height);		
	}
	
	public GsnScrollTextureRegion(TextureRegion region) {
		super(region.getTexture(), region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());
		
	}
	
	public void scroll(int deltaY){
		this.setRegionY(getRegionY() + deltaY);
	}
}
