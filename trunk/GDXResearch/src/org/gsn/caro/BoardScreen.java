package org.gsn.caro;

import java.util.Timer;
import java.util.TimerTask;

import org.gsn.engine.Debug;
import org.gsn.engine.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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
		IN_READY, IN_WAITING, IN_PLAY, IN_END
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
	Sprite background;
	Sprite waitOpponent;

	Timer timer = new Timer();
	final float WIDTH = Constant.WIDTH;

	Sprite winSprite;
	CaroGame game;
	float rotationSpeed;
	public float time = 0;

	public BoardScreen(CaroGame game) {
		this.game = game;
		rotationSpeed = 0.5f;
		guiCam = new OrthographicCamera(WIDTH, HEIGHT);
		guiCam.position.set(0, 0, 0);
		globalBatcher = new SpriteBatch();
		localBatcher = new SpriteBatch();

		background = CaroAssets.background;
		background.setScale(WIDTH / background.getWidth(), HEIGHT / background.getHeight());
		Utility.setCenter(background, WIDTH / 2, HEIGHT / 2);

		board = CaroAssets.board;
		// board.setPosition(2000 - 160, 2000 - 160);
		Utility.setCenter(board, guiCam.position.x, guiCam.position.y);

		pieceX = CaroAssets.pieceX;
		pieceO = CaroAssets.pieceO;
		ready = CaroAssets.ready;
		Utility.moveToCenter(ready, board);
		winSprite = CaroAssets.win;
		Utility.moveToCenter(winSprite, board);
		loseSprite = CaroAssets.lose;
		Utility.moveToCenter(loseSprite, board);
		back = CaroAssets.back;
		back.setPosition(WIDTH - back.getWidth(), HEIGHT - back.getHeight());
		// Debug.trace(back.getBoundingRectangle());
		waitOpponent = CaroAssets.waitOpponent;
		Utility.moveToCenter(waitOpponent, board);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	public void startgame() {
		Debug.trace("startGame");
		logic.newGame(1);
		state = State.IN_PLAY;
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

		case Input.Keys.F3:
			startgame();
			break;

		case Input.Keys.A:
			zoomCamera(0.2f);
			break;
		case Input.Keys.Q:
			zoomCamera(-0.2f);
			break;
		case Input.Keys.LEFT:
			if (guiCam.position.x > -10)
				guiCam.translate(-3, 0, 0);
			break;
		case Input.Keys.RIGHT:
			if (guiCam.position.x < 10)
				guiCam.translate(3, 0, 0);
			break;

		case Input.Keys.DOWN:
			if (guiCam.position.y > -10)
				guiCam.translate(0, -3, 0);
			break;

		case Input.Keys.UP:
			if (guiCam.position.y < 10)
				guiCam.translate(0, 3, 0);
			break;
		case Input.Keys.W:
			guiCam.rotate(-rotationSpeed, 0, 0, 1);
			break;
		case Input.Keys.E:
			guiCam.rotate(rotationSpeed, 0, 0, 1);
			break;
		}
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
		// Debug.trace("delta: " + delta);
		time += delta;
		GLCommon gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
//		gl.glViewport(100, 100, 200, 200);
//		guiCam.viewportWidth = 10;
//		guiCam.viewportHeight = 10;
		
		guiCam.update();
		// local
		localBatcher.begin();
		localBatcher.disableBlending();
		background.draw(localBatcher);
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
		case IN_WAITING:
			waitOpponent.draw(globalBatcher);
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

		localBatcher.begin();
		localBatcher.enableBlending();
		back.draw(localBatcher);

		CaroAssets.font.draw(localBatcher, "fps: " + Gdx.graphics.getFramesPerSecond(), 0, 20);
		localBatcher.end();
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
		Debug.trace("resize");

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		Debug.trace("resume");

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Debug.trace("show");
	}

	public boolean danh(int x, int y) {
		// TODO Auto-generated method stub
		localTouch.set(x, y, 0);
		globalTouch.set(x, y, 0);
		guiCam.unproject(globalTouch);
		localTouch.set(x, HEIGHT - y, 0);
		//Debug.trace("local    : " + localTouch);
		// Debug.trace("global : " + globalTouch);
		// toa do so voi ban co
		if (Utility.pointInRectangle(back.getBoundingRectangle(), localTouch.x, localTouch.y)) {
			//Debug.trace("click Back");
			game.setScreen(CaroGame.LOBBY);
		}

		switch (state) {
		case IN_READY:
			if (Utility.pointInRectangle(ready.getBoundingRectangle(), globalTouch.x, globalTouch.y)) {
				state = State.IN_WAITING;
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

	boolean dragged = false;
	Vector3 oldTouch;

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		Vector3 touch = new Vector3(x, HEIGHT - y, 0);
		// guiCam.unproject(touch);
		if (dragged)
			translateCamera((-touch.x + oldTouch.x) / guiCam.zoom, (-touch.y + oldTouch.y) / guiCam.zoom);
		oldTouch = touch;
		dragged = true;
		// Debug.trace("draged: " + touch);
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		if (!dragged)
			danh(x, y);
		dragged = false;
		return true;
	}

	final Rectangle cameraBound = new Rectangle(-300, -300, 600, 600);
	final float MAX_ZOOM = 2;
	final float MIN_ZOOM = 0.5f;

	public void translateCamera(float x, float y) {
		float nX = guiCam.position.x + x;
		float nY = guiCam.position.y + y;
		if (Utility.pointInRectangle(cameraBound, nX, nY)) {
			// Debug.trace("new Camera : " + nX + " " + nY);
			guiCam.translate(x, y, 0);
		}
	}

	public void zoomCamera(float zoom) {
		//Debug.trace(" zoom : " + guiCam.zoom);
		float nz = guiCam.zoom + zoom;
		if (nz <= MAX_ZOOM && nz >= MIN_ZOOM) {
			guiCam.zoom += zoom;
		}
	}
}
