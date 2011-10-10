package org.gsn.caro;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Utility {
	public static Vector2 getCenter(Sprite sprite) {
		return new Vector2(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
	}

	public static void setCenter(Sprite sprite, float x, float y) {
		sprite.setPosition(0, 0);
		Vector2 cen = getCenter(sprite);
		sprite.setPosition(x - cen.x, y - cen.y);
	}

	public static void moveToCenter(Sprite in, Sprite out) {
		Vector2 cenOut = getCenter(out);
		setCenter(in, cenOut.x, cenOut.y);
	}

	public static void drawCenter(SpriteBatch batcher, Sprite in, Sprite out) {
		moveToCenter(in, out);
		in.draw(batcher);
	}

	public static boolean pointInRectangle(Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}
}
