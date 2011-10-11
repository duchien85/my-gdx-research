package org.gsn.caro;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class AvatarSprite{
	Sprite avatar;
	final int witdth = 77;
	final int height = 110;
	
	public AvatarSprite(Sprite a){
		
	}
	
	public AvatarSprite(){
		this(0, 0);
	}
	
	public AvatarSprite(float x, float y) {
		// TODO Auto-generated constructor stub		
		avatar = new Sprite(CaroAssets.avatar);
		setPos(x, y);		
	}
	
	public void setPos(float x, float y){
		avatar.setPosition(x, y);
	}
		
	public void draw(SpriteBatch spriteBatch) {
		// TODO Auto-generated method stub
		avatar.draw(spriteBatch);
	}
	
	public void local(){
		
	}
}
