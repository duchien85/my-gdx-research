package org.gsn.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class CustomSprite {
	protected Rectangle bound = new Rectangle();

	abstract public void draw(SpriteBatch batcher);

	public float getHeight() {
		return bound.getHeight();
	}

	public float getWidth() {
		return bound.getWidth();
	}

	public float getX() {
		return bound.x;
	}

	public float getY() {
		return bound.y;
	}
	
	public float getMiddleX() {
		return bound.x + bound.getWidth()/2;
	}

	public float getMiddleY() {
		return bound.y + bound.getHeight()/2;
	}	

	public static Vector2 getCenter(CustomSprite sprite) {
		return new Vector2(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
	}

	private static void _setCenter(CustomSprite sprite, float x, float y) {
		Vector2 cen = getCenter(sprite);
		sprite.setPosition(x - cen.x, y - cen.y);
	}

	public void setCenter(float x, float y) {
		_setCenter(this, x, y);
	}

	public void setPosition(float x, float y) {
		bound.setX(x);
		bound.setY(y);
	}

	public void touchDown(float localX, float localY) {

	}

	public void touchUp(float localX, float localY) {

	}

	public void touchDragged(float localX, float localY) {

	}

	public Rectangle getBound() {
		return bound;
	}

	public boolean isTouchIn(float x, float y) {
		return Utility.pointInRectangle(bound, x, y);
	}
}
