package com.gsn.screen;

import javax.media.opengl.GL;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gsn.asset.CaroAssetManager;
import com.gsn.asset.FontManager;

import engine.Debug;


public class LobbyScreen extends InputAdapter implements Screen {
	private CaroAssetManager asset;
	
	private SpriteBatch localBatch;
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0f, 0.5f, 0.5f, 1f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		localBatch.begin();
		
		localBatch.draw(asset.avatar, 10, 10);
		FontManager.font.draw(localBatch,"trung", 30, 30);
		localBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		asset = CaroAssetManager.getInstance();
		localBatch = new SpriteBatch();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		Debug.trace("lobby", "touch down : "  + x + " "  + y + " " + pointer);
		return true;
	}
}
