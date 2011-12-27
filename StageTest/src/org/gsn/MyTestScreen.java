package org.gsn;

import java.util.Arrays;

import org.gsn.game.Constant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MyTestScreen implements Screen {	
	SpriteBatch batch = new SpriteBatch();
	
	
	public ParticleEffect pEffect;
	public MyTestScreen(){
		
		pEffect = new ParticleEffect();
		pEffect.load(Gdx.files.internal("particle/blow.p"), Gdx.files.internal("particle"));
	}
	@Override	
	public void render(float delta) {
		batch.begin();
		pEffect.draw(batch,delta);
		batch.end();
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
