package org.gsn.caro;

import java.util.Timer;
import java.util.TimerTask;

import org.gsn.engine.Debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class BoardScreen implements Screen {
	enum State {
		IN_READY, IN_PLAY, IN_END,
	}

	Sprite board;
	Sprite pieceX;
	Sprite pieceO;
	Sprite ready;
	Sprite winSprite;

	Timer timer = new Timer();
	boolean isWin;

	State state;

	final float kc_le = 15;
	final float kc_o = 20;

	SpriteBatch batcher;
	CaroLogic logic;
	OrthographicCamera guiCam;
	final float WIDTH = Assets.WIDTH;
	final float HEIGHT = Assets.HEIGHT;

	Vector3 localTouch = new Vector3();
	Vector3 globalTouch = new Vector3();

	public BoardScreen() {
		logic = new CaroLogic();
		logic.newGame(1);
		state = State.IN_READY;

		guiCam = new OrthographicCamera(WIDTH, HEIGHT);
		guiCam.position.set(2000, 2000, 0);
		batcher = new SpriteBatch();

		board = new Sprite(CaroAssets.board);
		board.setPosition(2000 - 160, 2000 - 160);

		pieceX = new Sprite(CaroAssets.pieceX);
		pieceO = new Sprite(CaroAssets.pieceO);
		ready = new Sprite(CaroAssets.ready);
		Utility.moveToCenter(ready, board);
		winSprite = new Sprite(CaroAssets.win);
		Utility.moveToCenter(winSprite, board);
	}

	private void update() {
		if (Gdx.input.justTouched()) {
			float x = Gdx.input.getX();
			float y = Gdx.input.getY();
			localTouch.set(x, y, 0);

			globalTouch.set(x, y, 0);

			guiCam.unproject(globalTouch);
			Debug.trace("local : " + localTouch);
			Debug.trace("global : " + globalTouch);
			// toa do so voi ban co
			switch (state) {
			case IN_READY:
				if (Utility.pointInRectangle(ready.getBoundingRectangle(), globalTouch.x, globalTouch.y)) {
					Debug.trace("ready");
					logic.newGame(1);
					state = State.IN_PLAY;
				}
				break;
			case IN_PLAY:
				x = globalTouch.x - board.getX();
				y = globalTouch.y - board.getY();

				int row = (int) ((x - kc_le) / kc_o);
				int col = (int) ((y - kc_le) / kc_o);
				logic.chessMove(logic.getTurn(), row, col);
				if (logic.getCount() >= 5)
					win();
				break;
			case IN_END:
				break;
			}
		}
	}

	@Override
	public void render(float delta) {
		update();
		GLCommon gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		batcher.enableBlending();
		batcher.setProjectionMatrix(guiCam.combined);
		batcher.begin();
		board.draw(batcher);
		renderBoard();
		switch (state) {
		case IN_READY:
			ready.draw(batcher);
			break;
		case IN_PLAY:
			break;
		case IN_END:
			if (isWin)
				winSprite.draw(batcher);
			else
				winSprite.draw(batcher);
			break;
		}

		batcher.end();
	}

	public void win() {
		state = State.IN_END;
		isWin = true;
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				state = State.IN_READY;
				logic.newGame(2);
			}
		}, 2000);
	}

	private void renderBoard() {
		for (int i = 0; i < CaroLogic.SIZE; i++)
			for (int j = 0; j < CaroLogic.SIZE; j++) {
				switch (logic.getCell(i, j)) {
				case CaroLogic.EMPTY:
					break;
				case CaroLogic.pO:
					pieceO.setPosition(board.getX() + kc_le + i * kc_o, board.getY() + kc_le + j * kc_o);
					pieceO.draw(batcher);
					break;
				case CaroLogic.pX:
					pieceX.setPosition(board.getX() + kc_le + i * kc_o, board.getY() + kc_le + j * kc_o);
					pieceX.draw(batcher);
					break;
				default:
					break;
				}
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
