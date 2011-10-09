package org.gsn.caro;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LobyScreen implements Screen {
	SpriteBatch globalBatcher;
	SpriteBatch localBatcher;
	CaroLogic logic;
	OrthographicCamera guiCam;
	final float WIDTH = Constant.WIDTH;
	final float HEIGHT = Constant.HEIGHT;
	public void update(){
			
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		update();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

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

}
