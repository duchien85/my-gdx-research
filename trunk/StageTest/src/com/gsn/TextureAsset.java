package com.gsn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class TextureAsset {
	private static TextureAtlas atlas;
	public static AtlasRegion avatar;
	public static AtlasRegion monkey;
	public static void load(){
		atlas = new TextureAtlas(Gdx.files.internal("data/pack"));
		avatar = atlas.findRegion("avatar");
		monkey = atlas.findRegion("lose");
	}
}
