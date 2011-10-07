package org.gsn.engine;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GSNSpriteBatch extends SpriteBatch{
	private Camera camera;
	public GSNSpriteBatch(Camera camera){
		this.camera = camera;
	}	
	@Override
	public void begin() {
		// TODO Auto-generated method stub
		
		super.begin();
	}

}
