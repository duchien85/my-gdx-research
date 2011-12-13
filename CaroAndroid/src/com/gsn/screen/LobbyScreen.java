package com.gsn.screen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gsn.asset.CaroAssetManager;
import com.gsn.asset.FontManager;

import engine.gdx.ImageFactory;
import engine.gdx.ImageFactory.IImageFactoryListener;;


public class LobbyScreen extends InputAdapter implements Screen {
	private CaroAssetManager asset;
	
	private SpriteBatch localBatch;		
	private Texture avatarTexture;
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		if (finish){
			FileHandle file = Gdx.files.external(Gdx.files.getExternalStoragePath() + "/other.png");			
			avatarTexture = new Texture(file);
			finish = false;
		}
		Gdx.gl.glClearColor(0f, 0.5f, 0.5f, 1f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		localBatch.begin();
		
		localBatch.draw(asset.avatar, 100, 100);
		if (avatarTexture != null)
			localBatch.draw(avatarTexture, 10, 10);
		FontManager.font.setColor(0.5f, 0.5f, 0, 1f);
		FontManager.font.draw(localBatch,"trung", 30, 30);
		localBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	boolean finish = false;
	@Override
	public void show() {
		avatarTexture = new Texture(Gdx.files.internal("gdx/other.png"));
		// TODO Auto-generated method stub
		asset = CaroAssetManager.getInstance();
		localBatch = new SpriteBatch();
		FontManager.font.setColor(1, 1, 1, 1);
		String link = "http://vnexpress.net/Files/Subject/3b/bb/d7/06/250_Dac_diem_nhung_xe_may_bi_chay.jpg";
		int width = 64;
		int height = 64;
		int quality = 85;
		OutputStream out;
//		try {
//			out = new FileOutputStream(Environment.getExternalStorageDirectory() + "/other.png");
//			ImageFactory.saveBitmapToFileAsync(link, width, height, quality, out, new IImageFactoryListener() {
//				
//				@Override
//				public void onFinishSaving() {
//					// TODO Auto-generated method stub
//					finish = true;
//				}
//				
//				@Override
//				public void onError(Exception e) {
//					// TODO Auto-generated method stub
//					
//				}
//			});
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}		
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
		Log.i("lobby", "touch down : "  + x + " "  + y + " " + pointer);
		return true;
	}
}
