package org.gsn.caro;

import java.util.Timer;
import java.util.TimerTask;

import org.gsn.engine.Debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class BoardScreen extends InputAdapter implements Screen {
	private class EndEnimationTask extends TimerTask {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			state = State.IN_READY;
			logic.newGame(2);
		}
	}

	enum State {
		IN_END, IN_PLAY, IN_READY,
	}

	Sprite back;
	Sprite board;
	SpriteBatch globalBatcher;
	Vector3 globalTouch = new Vector3();
	OrthographicCamera guiCam;
	final float HEIGHT = Constant.HEIGHT;

	boolean isWin;
	final float kc_le = 15;

	final float kc_o = 20;

	SpriteBatch localBatcher;
	Vector3 localTouch = new Vector3();

	CaroLogic logic;
	Sprite loseSprite;
	Sprite pieceO;
	Sprite pieceX;
	Sprite ready;
	State state;

	Timer timer = new Timer();
	final float WIDTH = Constant.WIDTH;

	Sprite winSprite;
	CaroGame game;

	public BoardScreen(CaroGame game) {
		this.game = game;

		guiCam = new OrthographicCamera(WIDTH, HEIGHT);
		guiCam.position.set(2000, 1000, 0);
		globalBatcher = new SpriteBatch();
		localBatcher = new SpriteBatch();

		board = new Sprite(CaroAssets.board);
		// board.setPosition(2000 - 160, 2000 - 160);
		Utility.setCenter(board, guiCam.position.x, guiCam.position.y);

		pieceX = new Sprite(CaroAssets.pieceX);
		pieceO = new Sprite(CaroAssets.pieceO);
		ready = new Sprite(CaroAssets.ready);
		Utility.moveToCenter(ready, board);
		winSprite = new Sprite(CaroAssets.win);
		Utility.moveToCenter(winSprite, board);
		loseSprite = new Sprite(CaroAssets.win);
		Utility.moveToCenter(loseSprite, board);
		back = new Sprite(CaroAssets.back);
		back.setPosition(WIDTH - back.getWidth(), HEIGHT - back.getHeight());
		Debug.trace(back.getBoundingRectangle());
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		// Debug.trace(character);
		switch (keycode) {
		case Input.Keys.F1:
			win();
			break;
		case Input.Keys.F2:
			lose();
			break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		Debug.trace(character);
		return true;
	}

	public void lose() {
		state = State.IN_END;
		isWin = false;
		timer.schedule(new EndEnimationTask(), 2000);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		GLCommon gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		// local
		localBatcher.begin();
		back.draw(localBatcher);
		CaroAssets.font.draw(localBatcher, "fps: " + Gdx.graphics.getFramesPerSecond(), 0, 20);
		localBatcher.end();

		// global
		globalBatcher.enableBlending();
		globalBatcher.setProjectionMatrix(guiCam.combined);
		globalBatcher.begin();
		board.draw(globalBatcher);
		renderBoard();
		switch (state) {
		case IN_READY:
			ready.draw(globalBatcher);
			break;
		case IN_PLAY:
			break;
		case IN_END:
			if (isWin)
				winSprite.draw(globalBatcher);
			else
				loseSprite.draw(globalBatcher);
			break;
		}
		globalBatcher.end();
	}

	private void renderBoard() {
		for (int i = 0; i < CaroLogic.SIZE; i++)
			for (int j = 0; j < CaroLogic.SIZE; j++) {
				switch (logic.getCell(i, j)) {
				case CaroLogic.EMPTY:
					break;
				case CaroLogic.pO:
					pieceO.setPosition(board.getX() + kc_le + i * kc_o, board.getY() + kc_le + j * kc_o);
					pieceO.draw(globalBatcher);
					break;
				case CaroLogic.pX:
					pieceX.setPosition(board.getX() + kc_le + i * kc_o, board.getY() + kc_le + j * kc_o);
					pieceX.draw(globalBatcher);
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
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub

		localTouch.set(x, y, 0);

		globalTouch.set(x, y, 0);

		guiCam.unproject(globalTouch);
		Debug.trace("GDX    : " + localTouch);
		localTouch.set(x, HEIGHT - y, 0);
//		Debug.trace("local    : " + localTouch);
//		Debug.trace("global : " + globalTouch);
		Debug.trace("pointer :" + pointer);
		// toa do so voi ban co
		if (Utility.pointInRectangle(back.getBoundingRectangle(), localTouch.x, localTouch.y)) {
			Debug.trace("click Back");
			game.setScreen(CaroGame.LOBBY);
		}

		switch (state) {
		case IN_READY:
			if (Utility.pointInRectangle(ready.getBoundingRectangle(), globalTouch.x, globalTouch.y)) {
				Debug.trace("ready");
				logic.newGame(1);
				state = State.IN_PLAY;
			}
			break;
		case IN_PLAY:
			float fx = globalTouch.x - board.getX();
			float fy = globalTouch.y - board.getY();

			int row = (int) ((fx - kc_le) / kc_o);
			int col = (int) ((fy - kc_le) / kc_o);
			chessMove(logic.getTurn(), row, col);
			break;
		case IN_END:
			break;
		}
		return true;
	}

	public boolean chessMove(int turn, int row, int col) {
		if (logic.chessMove(turn, row, col)) {
			CaroAssets.hitSound.play();
			return true;
		}
		return false;

	}

	public void win() {
		state = State.IN_END;
		isWin = true;
		timer.schedule(new EndEnimationTask(), 2000);
	}

	public void clearGame() {
		// TODO Auto-generated method stub
		logic = new CaroLogic();
		logic.newGame(1);
		state = State.IN_READY;
	}

}
