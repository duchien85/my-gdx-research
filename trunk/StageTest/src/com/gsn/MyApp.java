package com.gsn;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.badlogic.gdx.scenes.scene2d.actors.Label;

public class MyApp implements ApplicationListener {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JoglApplication(new MyApp(), "Stage Test", 480, 320, false);
		//new JoglApplication(new MyApp(), "Stage Test", 240, 160, false);
	}
	Texture texture;
	BitmapFont font;
	Stage stage;
	@Override
	public void create() {
		// TODO Auto-generated method stub
		stage = new Stage(480, 320, true);
		
		texture = new Texture(Gdx.files.internal("data/badlogicsmall.jpg"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font = new BitmapFont();								
		
		Group group = new Group("group");
		
		Label fps = new Label("fps", font, "fps: 0");
		fps.x = 10;
		fps.y = 30;
		fps.color.set(0, 1, 0, 1);
		group.addActor(fps);
		
		Image img = new Image("badicon", texture);
		img.x = 100;
		img.y = 200;
		group.addActor(img);
		
		stage.addActor(group);
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
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
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
