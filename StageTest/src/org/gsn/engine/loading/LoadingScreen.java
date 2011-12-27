package org.gsn.engine.loading;

import java.util.List;

import org.gsn.engine.Debug;
import org.gsn.engine.GSNScreen;
import org.gsn.game.CaroAssets;
import org.gsn.game.CaroConfig;

import org.gsn.game.CaroGame;
import org.gsn.game.CaroSettings;
import org.gsn.game.CaroGame.ScreenID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class LoadingScreen extends GSNScreen{

	public BitmapFont font;
	Image chuLoading;
	TextureRegion dauCham;
	private final float STARTX = 220;
	private final float STARTY = 5;
	Image background;
	private int dem = 1;
	List<Image> diem;
	
	public LoadingScreen(CaroGame game) {
		super(game);
		if (!game.canPlayGame) return;
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("gdx/loading.txt"));
		background = new Image(atlas.findRegion("loadingBg"));
		chuLoading = new Image(atlas.findRegion("chuLoading"));
		dauCham = atlas.createSprite("dauCham");
		font = new BitmapFont(Gdx.files.internal("font/comic.fnt"), Gdx.files.internal("font/comic.png"), false);
		font.setColor(0.9f, 0.9f, 0.5f, 1);		
		CaroConfig.load();
		CaroAssets.newInstance().loadAll();				
		CaroSettings.load();
//		background.x = WIDTH/2;
//		background.y = HEIGHT/2;
		chuLoading.x = STARTX;
		chuLoading.y = STARTY;		
		stage.addActor(background);
		stage.addActor(chuLoading);
		
	}

	float oldTime = 0;
	float time = 0;

	@Override
	public void render(float delta) {
		super.render(delta);
		if (!game.canPlayGame) return;
		time += delta;		
		if (time - oldTime > 0.5){
			oldTime = time;
			dem++;
			if (dem>3) dem = 0;
		}		
		if (CaroAssets.getInstance().update()){
			CaroAssets.getInstance().assignContent();
			game.initGame();
			game.setScreen(ScreenID.LOGIN);
			game.loadFinish = true;
		}
//		GLCommon gl = Gdx.gl;
//		gl.glClearColor(0, 0, 0, 1);
//		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//		localBatcher.begin();
//		background.draw(localBatcher);
//		localBatcher.draw(chuLoading, STARTX, STARTY);
		stage.getSpriteBatch().begin();
		for (int i = 0; i< dem; i++)
			stage.getSpriteBatch().draw(dauCham, STARTX+chuLoading.getPrefWidth()+ i*5, STARTY+4);
		stage.getSpriteBatch().end();

	}

}
