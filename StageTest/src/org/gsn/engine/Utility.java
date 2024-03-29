package org.gsn.engine;


import java.util.List;

import org.gsn.engine.GsnTextureAtlas.AtlasRegion;
import org.gsn.game.CaroAssets;
import org.gsn.game.Constant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	
	public static boolean pointInRectangle(float rx, float ry, float rw, float rh,  float x, float y) {
		return rx <= x && rx + rw >= x && ry <= y && ry + rh >= y;
	}
	
	public static Vector2 getCenter(TextureRegion tex, Sprite out) {
		Vector2 cenOut = getCenter(out);
		return new Vector2(cenOut.x - tex.getRegionWidth() / 2, cenOut.y - tex.getRegionHeight() / 2);
	}
	
	public static GSNAnimation getAnimationFromFile(float frameDuration, String filename, String contentName){		
		GsnTextureAtlas atlas = new GsnTextureAtlas(Gdx.files.internal(filename),null);
		List<AtlasRegion> regions = atlas.findRegions(contentName);		
		return new GSNAnimation(frameDuration, regions);		
	}	
	
	public static GSNAnimation getAnimationFromAtlas(float frameDuration, TextureAtlas atlas, String contentName){
		List regions = atlas.findRegions(contentName);		
		return new GSNAnimation(frameDuration, regions);		
	}	
	
	public static String getBeTypeString(int type){
		if (type==Constant.BET_GOLD) return "gold";
		else
			if (type==Constant.BET_COIN) return "coin";
		return null;
	}
	
	public static void drawText(SpriteBatch sb, BitmapFont font, String text, float width, float height, float x, float y){
		font.draw(sb, text, x, y);
	}
	
	public static void drawNumber(SpriteBatch sb, int num, float x, float y){
		String s = String.valueOf(num);
		com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion tex;
		float dx = x;
		for (int i=0; i<s.length(); i++){
			tex = CaroAssets.getInstance().numbers.get(s.charAt(i)-48);
			sb.draw(tex, dx, y);
			dx+= tex.getRegionWidth();
		}
	}
	
	public static void drawNumberFromTexture(List<com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion> numbers, SpriteBatch sb, int num, float x, float y){
		String s = String.valueOf(num);
		com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion tex;
		float dx = x;
		for (int i=0; i<s.length(); i++){
			tex = numbers.get(s.charAt(i)-48);
			sb.draw(tex, dx, y);
			dx+= tex.getRegionWidth();
		}
	}
	
	public static String formatGold(int gold){
		if (gold>= 1000000000) return String.valueOf(gold/1000000000) +"B";
		else
			if (gold>= 1000000) return String.valueOf(gold/1000000) +"M";
			else
				if (gold>= 1000) {
					int x = gold % 1000;
					return String.valueOf(gold/1000)+"."+ String.valueOf(x/100) +"K";
				}
				else return String.valueOf(gold);
	}
	
	public static String formatNumber(int value){
		if (value>= 1000) {			
			String s = String.valueOf(value % 1000);
			while (s.length()<3) s="0"+s;
			return String.valueOf(value/1000)+"."+ s;
		}
		else return String.valueOf(value);
	}

	public static int findPower(int x) {
		int mu = (int) (Math.ceil(Math.log(x) / Math.log(2)));
		return (int) Math.pow(2, mu);
	}
	
	public static String getShortName(String name){
		if (name.length()>=9) return (name.substring(0, 6)+"...");
		else return name;
	}
}
