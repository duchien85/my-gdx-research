package com.gsn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureAsset {
	private static TextureAtlas atlas;
	public static AtlasRegion avatar;
	public static void load(){
		atlas = new TextureAtlas(Gdx.files.internal("gdx/pack"));
		avatar = atlas.findRegion("avatar");
	}
}
