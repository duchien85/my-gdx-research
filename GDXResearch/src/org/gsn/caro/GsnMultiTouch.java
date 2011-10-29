package org.gsn.caro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

public class GsnMultiTouch {
	private final int POINTER = 2;
	private boolean pressAlt = false;
	InputProcessor input = new InputAdapter();

	public GsnMultiTouch(InputProcessor input) {
		this.input = input;
	}

	public void keyUp(int keycode) {
		switch (keycode) {
		case Input.Keys.ALT_LEFT:
			int x, y;			
			pressAlt = false;
			x = Gdx.graphics.getWidth() / 2;
			y = Gdx.graphics.getHeight() / 2;
			input.touchUp(x, y, POINTER, 0);
			break;
		}
	}

	public void keyDown(int keycode) {
		switch (keycode) {
		case Input.Keys.ALT_LEFT:
			if (!pressAlt) {
				int x, y;
				pressAlt = true;
				x = Gdx.graphics.getWidth() / 2;
				y = Gdx.graphics.getHeight() / 2;
				input.touchDown(x, y, POINTER, 0);
			}
			break;
		}
	}

	public void touchDraged(int x, int y, int pointer) {

	}

	public void touchUp(int x, int y, int pointer) {

	}

	public void touchDown(int x, int y, int pointer) {

	}
}
