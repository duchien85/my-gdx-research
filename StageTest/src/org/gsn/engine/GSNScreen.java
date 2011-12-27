package org.gsn.engine;

import java.util.ArrayList;
import java.util.List;


import org.gsn.game.CaroAssetManager;
import org.gsn.game.CaroAssets;
import org.gsn.game.CaroGame;
import org.gsn.game.Constant;

import android.Manifest.permission_group;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public abstract class GSNScreen implements Screen {
	float timeGame;	
	public static final float WIDTH = Constant.WIDTH;
	public static final float HEIGHT = Constant.HEIGHT;
	
	public static final boolean STRETCH = true;
	
//	public static final float WIDTH = Gdx.graphics.getWidth();
//	public static final float HEIGHT = Gdx.graphics.getHeight();
	protected CaroGame game;
	public ParticleEffect pEffect;
	boolean isShowEffect = false;
	float timeEffectStart = 0;
	public Stage stage;	
	public GSNScreen() {
	}

	public CaroGame getGame() {
		return game;
	}
	
	public static CaroAssetManager getAssets() {
		return CaroAssets.getInstance();
	}

	public GSNScreen(CaroGame game) {
		stage = new Stage(WIDTH, HEIGHT, STRETCH){
			@Override
			public boolean keyDown(int keycode) {				
				super.keyDown(keycode);
				return GSNScreen.this.keyDown(keycode);
			}
			@Override
			public boolean touchUp(int x, int y, int pointer, int button) {				
				Vector2 vt= new Vector2(0, 0);
				toStageCoordinates(x, y, vt);
//				System.out.println("touch up: "+vt.x+" "+vt.y);
//				System.out.println("touch up2: "+x+" "+y);
				GSNScreen.this.touchUp(vt.x, vt.y);
				return super.touchUp(x, y, pointer, button);
			}
		};
		//stage.toStageCoordinates(x, y, out)		
		//stage.hit(x, y)
		this.game = game;		
	}

	public void touchUp(float x, float y){
//		System.out.println("touch up: "+actor.toString());
	}
	
	public static boolean isTouchIn(Actor act, float x, float y){
		return (act.x <x && x< (act.x+act.width) && act.y<y && (y<act.y+act.height));
	}
	
	public static ImageButton createButton(TextureRegion up, TextureRegion down, float width, float height){
		ImageButton btn = new ImageButton(up, down);		
		btn.width = width;
		btn.height = height;
		return btn;
	}
	
	public static Image createImage(TextureRegion bg, float width, float height){
		Image img = new Image(bg);
		img.width = width;
		img.height = height;
		return img;
	}
	
	public void moveTo(Actor actor, float x, float y){
		actor.x = x;
		actor.y = y;
	}
	
	public boolean keyDown(int keycode){
		return true;
	}
	public void onSetScreen() {
		
	};

	public void showEffect(float x, float y) {
		
	}

	@Override
	public void render(float delta) {
		GL10 gl = Gdx.graphics.getGL10();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		timeGame += delta;		
		if (stage!=null){
			stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30.0f));
			stage.draw();
		}
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
