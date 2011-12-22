package com.gsn;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.badlogic.gdx.scenes.scene2d.actors.Label;

public class MyApp implements ApplicationListener{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JoglApplication(new MyApp(), "Stage Test", 480, 320, false);
		//new JoglApplication(new MyApp(), "Stage Test", 240, 160, false);
	}
	
	static final String tag = "MyApp";	
	Stage stage;	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		TextureAsset.load();			
		setStage(new MyStage(480, 320, true));
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
		Gdx.input.setInputProcessor(this.stage);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		Gdx.app.log("screen", "resize: " + width + " " + height);
		//stage.setViewport(width, height, true);	
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	//	Gdx.gl.glClearColor(1.0f, 0, 0, 1);
		if (stage != null){
			stage.act(Gdx.graphics.getDeltaTime());
			stage.draw();
		}
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
