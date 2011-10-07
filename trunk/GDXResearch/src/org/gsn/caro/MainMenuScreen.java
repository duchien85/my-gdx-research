/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package org.gsn.caro;

import org.gsn.engine.Debug;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen implements Screen {
	OrthographicCamera guiCam;
	SpriteBatch batcher;	
	Vector3 touchPoint;
	Sprite soundSprite; 
	final float WIDTH = Assets.WIDTH;
	final float HEIGHT = Assets.HEIGHT;
	
	public MainMenuScreen (Game game) {
		
		soundSprite = new Sprite(Assets.soundOn);
		soundSprite.setPosition(WIDTH / 2, HEIGHT / 2);
		
		guiCam = new OrthographicCamera(WIDTH, HEIGHT);
		guiCam.position.set(WIDTH / 2, HEIGHT / 2, 0);
		batcher = new SpriteBatch();		
		touchPoint = new Vector3();
	}


	public void update (float deltaTime) {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (OverlapTester.pointInRectangle(soundSprite.getBoundingRectangle(), touchPoint.x, touchPoint.y)) {
				Debug.trace("click Sound");
				Assets.playSound(Assets.clickSound);						
			}
		}
	}

	@Override
	public void render(float deltaTime) {
		update(deltaTime);
		GLCommon gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);

		batcher.disableBlending();
//		batcher.begin();
//		batcher.draw(Assets.backgroundRegion, 0, 0, 320, 480);
//		batcher.end();

		batcher.enableBlending();
		batcher.begin();
		soundSprite.draw(batcher);
		batcher.end();
	}

	@Override
	public void pause () {		
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
}
