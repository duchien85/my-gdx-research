package org.gsn.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class CustomSprite {
	 protected Rectangle rectBound = new Rectangle();
	 protected float x;	 
	 protected float y;	

	 abstract public void draw(SpriteBatch batcher);
	 
	 public float getHeight() {
		// TODO Auto-generated method stub
		return rectBound.getHeight();
	}	 
	 
	 public Rectangle getRectangleBounding(){
		 return rectBound;
	 }
	 
	 public float getWidth() {
		// TODO Auto-generated method stub
		return rectBound.getWidth();
	}
	 
	 public float getX(){
		 return x;
	 }
	 	 
	 public float getY(){
		 return y;
	 }
	 
	 public void setCenter(float x, float y){
		 Utility.setCenter(this, x, y);
	 }

	public void setPosition(float x, float y){
		 this.x = x;
		 this.y = y;
		 rectBound.setX(x);
		 rectBound.setY(y);
	 }

	public void touchDown(float localX, float localY){
		 
	 }	 
}
