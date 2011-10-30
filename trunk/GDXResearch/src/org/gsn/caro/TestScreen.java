package org.gsn.caro;

import org.gsn.engine.Debug;
import org.gsn.engine.Utility;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;

public class TestScreen extends InputAdapter implements Screen, OnActionCompleted {
	SpriteBatch batcher = new SpriteBatch();
	TextureRegion tex = CaroAssets.atlas.findRegion("cuoc1G");
	GsnScrollTextureRegion scroll = new GsnScrollTextureRegion(tex);
	public TestScreen() {
		// TODO Auto-generated constructor stub
		Debug.trace("old : "  + Utility.RegionToString(scroll) );
		//Gdx.graphics.g
		
		scroll.scroll(20);
		Debug.trace("new : "  + Utility.RegionToString(scroll) );
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		batcher.begin();
		batcher.draw(scroll, 100, 100);		
		batcher.end();
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
