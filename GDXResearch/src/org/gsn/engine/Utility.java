package org.gsn.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Utility {
	public static Vector2 getCenter(Sprite sprite) {
		return new Vector2(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
	}
	
	public static Vector2 getCenter(CustomSprite sprite) {
		return new Vector2(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
	}

	public static void setCenter(Sprite sprite, float x, float y) {		
		Vector2 cen = getCenter(sprite);
		sprite.setPosition(x - cen.x, y - cen.y);
	}
	
	public static void setCenter(CustomSprite sprite, float x, float y) {		
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

	public static Animation animation(String file, float frameDuration, int x, int y, int width, int height, int col, int row) {
		Texture texture = new Texture(Gdx.files.internal(file));
		return animation(texture, frameDuration, x, y, width, height, col, row);
	}

	public static Animation animation(Texture texture, float frameDuration, int x, int y, int width, int height, int col, int row) {
		TextureRegion region = new TextureRegion(texture, x, y, width, height);
		TextureRegion[][] tmp = region.split(width / col, height / row);
		TextureRegion[] frames = new TextureRegion[col * row];
		int index = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				frames[index++] = tmp[i][j];
			}
		}
		return new Animation(frameDuration, frames);
	}
}
