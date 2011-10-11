package org.gsn.caro;

import org.gsn.engine.Debug;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.scenes.scene2d.ui.List;

public class TestScreen extends InputAdapter implements Screen {	
	SpriteBatch batcher = new SpriteBatch();	
	float time = 0;
	MenuSpirte menu ;
	Sprite moc = new Sprite(CaroAssets.pieceX);
	public TestScreen() {
		// TODO Auto-generated constructor stub
		menu = new MenuSpirte(CaroAssets.font, 300, "play", "music", "pause");
		menu.setPosition(300, 300);
		moc.setPosition(300, 300);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		time += delta;
		batcher.begin();		
		menu.draw(batcher);	
		moc.draw(batcher);
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
		if (Utility.pointInRectangle(menu.getRectangleBounding(), x, y)){
			Debug.trace("click Menu");
			menu.touchDown(x - menu.getX(), y - menu.getY());
		}
		return true;
	}

}
