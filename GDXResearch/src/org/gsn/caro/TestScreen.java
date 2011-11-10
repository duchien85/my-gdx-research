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
	TextureRegion img;	
	
	public TestScreen() {
		// TODO Auto-generated constructor stub
		img = ImageManager.downloadToTexture("http://vnexpress.net/Files/Subject/3b/b0/4a/fa/130_MW_2011.jpg", 100, 100);
	  	//ImageManager.downloadImage("http://vnexpress.net/Files/Subject/3b/b0/4a/fa/130_MW_2011.jpg");		
		//img.
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		batcher.begin();
		batcher.draw(img, 100, 100);		
		batcher.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		Debug.trace("resize");
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Debug.trace("show");
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Debug.trace("hide");
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		Debug.trace("pause");
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		Debug.trace("resume");
		img = ImageManager.downloadToTexture("http://vnexpress.net/Files/Subject/3b/b0/4a/fa/130_MW_2011.jpg", 100, 100);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Debug.trace("dispose");
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
