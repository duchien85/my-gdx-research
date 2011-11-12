package org.gsn.caro;

import java.io.File;

import org.gsn.engine.Debug;
import org.gsn.engine.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;

public class TestScreen extends InputAdapter implements Screen, OnActionCompleted {
	SpriteBatch batcher = new SpriteBatch();
	//TextureRegion img;	
	int x, y;
	public TestScreen() {
		// TODO Auto-generated constructor stub		
	  	//img = CaroAssets.avatar;				
		String path = "data/caro/";
		String absoPath = Gdx.files.getExternalStoragePath() + path; 
		Debug.trace(absoPath);
		File f = new File(absoPath);
		if (f.mkdirs())
			Debug.trace("ok");
		else
			Debug.trace("failure");
		
		//ImageManager.saveImage("http://vnexpress.net/Files/Subject/3b/b0/4a/fa/130_MW_2011.jpg", Gdx.files.external(path + "trung.jpg").write(false));
		//img = ImageManager.downloadToTexture(Gdx.files.external(path + "trung.jpg").read(), 100, 100);
		CaroAssetManager.getInstance().finishLoadingPack("gdx/pack");
		Debug.trace("load dc pack roi : " +  CaroAssetManager.getInstance().isLoaded("gdx/pack"));
		Debug.trace("load dc avatar roi : " +  (CaroAssetManager.getInstance().getAvatarRegion() != null));
	}
	float oldtime = -100;
	float time = 0;
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub		
		CaroAssetManager.getInstance().update();
		if (time == 0)
			Debug.trace("bat dau ne");
		time += delta;
		
//		if (time - oldtime < 1f/24)
//			return;
//		oldtime = time;
		
		x = (Math.round(time * 20));
		x = x % Gdx.graphics.getWidth();
		y = 100;
		Gdx.gl.glClearColor(0, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		batcher.begin();
		
		batcher.draw(CaroAssetManager.getInstance().getAvatarRegion(), x, y);
		//batcher.draw(img, 0, 0);
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
		//img = ImageManager.downloadToTexture(Gdx.files.external("trung.jpg").read(), 100, 100);
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
