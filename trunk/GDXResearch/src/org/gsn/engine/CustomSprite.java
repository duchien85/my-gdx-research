package org.gsn.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class CustomSprite {
	 protected float x;
	 protected float y;	 
	 protected Rectangle rectBound = new Rectangle();	

	 public void setPosition(float x, float y){
		 this.x = x;
		 this.y = y;
		 rectBound.setX(x);
		 rectBound.setY(y);
	 }
	 
	 public void touchDown(float localX, float localY){
		 
	 }	 
	 
	 public Rectangle getRectangleBounding(){
		 return rectBound;
	 }
	 
	 public float getX(){
		 return x;
	 }
	 
	 public float getY(){
		 return y;
	 }
	 	 
	 
	 abstract public void draw(SpriteBatch batcher);

	public float getWidth() {
		// TODO Auto-generated method stub
		return rectBound.getWidth();
	}

	public float getHeight() {
		// TODO Auto-generated method stub
		return rectBound.getHeight();
	}	 
}
