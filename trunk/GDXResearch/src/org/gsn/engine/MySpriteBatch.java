package org.gsn.engine;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MySpriteBatch extends SpriteBatch{
	private Camera camera;
	public MySpriteBatch(Camera camera){
		this.camera = camera;
	}	
	@Override
	public void begin() {
		// TODO Auto-generated method stub
		
		super.begin();
	}

}
