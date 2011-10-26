package org.gsn.caro;

import org.gsn.engine.Debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.actors.Image;

public class TestScreen extends InputAdapter implements Screen, OnActionCompleted {
	SpriteBatch batcher = new SpriteBatch();
	float time = 0;
	Stage stage = new Stage(Constant.WIDTH, Constant.HEIGHT, true);
	Image img;

	public TestScreen() {
		// TODO Auto-generated constructor stub
		img = new com.badlogic.gdx.scenes.scene2d.actors.Image("trung", CaroAssets.avatar);
		img.width = img.height = 100;
		img.originX = 50;
		img.originY = 50;
		Delay delay = Delay.$(MoveBy.$(100, 100, 1).setCompletionListener(this), 3);
		delay.setCompletionListener(this);
		img.action(Sequence.$(delay).setCompletionListener(this));
		stage.addActor(img);
		Debug.trace("Width : " + Constant.WIDTH / Gdx.graphics.getPpiX() + " inc ");
		Debug.trace("Height : " + Constant.HEIGHT / Gdx.graphics.getPpiY() + " inc ");
		//Gdx.graphics.g
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		time += delta;
		GLCommon gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batcher.begin();
		// batcher.draw(CaroAssets.test, 20, 20);

		batcher.end();
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();

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

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		y = Constant.HEIGHT - y;

		return true;
	}

	@Override
	public void completed(Action action) {
		// TODO Auto-generated method stub
		Debug.trace("complete");

	}

}
