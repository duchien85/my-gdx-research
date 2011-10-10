package org.gsn.caro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class PackerAssets {
	public static TextureAtlas atlas;
	public static Sprite ready;

	public static void load() {
		atlas = new TextureAtlas(Gdx.files.internal("data/pack.txt"));
		ready = atlas.createSprite("ready");
	}
}
