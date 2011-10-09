package org.gsn.caro;

import org.gsn.engine.Debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class LobyScreen extends InputAdapter implements Screen {
	SpriteBatch globalBatcher;
	SpriteBatch localBatcher;	
	OrthographicCamera guiCam;
	final float WIDTH = Constant.WIDTH;
	final float HEIGHT = Constant.HEIGHT;
	Vector3 localTouch;
	Sprite all = new Sprite(CaroAssets.all);
	
	Sprite cuoc1G;
	CaroGame game;
	public LobyScreen(CaroGame game){
		this.game = game;
		localTouch = new Vector3();
		globalBatcher = new SpriteBatch();
		localBatcher = new SpriteBatch();
		cuoc1G = new Sprite(CaroAssets.cuoc1G);
		Utility.setCenter(cuoc1G, WIDTH / 2, HEIGHT / 2);
		guiCam = new OrthographicCamera(WIDTH, HEIGHT);
	}
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		Debug.trace("Pointer: " + pointer);
		localTouch.set(x, HEIGHT - y , 0);
		if (Utility.pointInRectangle(cuoc1G.getBoundingRectangle(), localTouch.x, localTouch.y)){
			game.setScreen(CaroGame.BOARD);
		}
		return true;
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		GLCommon gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		
		
		
		localBatcher.begin();
		localBatcher.disableBlending();
		//all.draw(localBatcher);		
		localBatcher.enableBlending();
		cuoc1G.draw(localBatcher);
		CaroAssets.font.draw(localBatcher, "fps: " + Gdx.graphics.getFramesPerSecond(), 0, 20);
		localBatcher.end();
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
