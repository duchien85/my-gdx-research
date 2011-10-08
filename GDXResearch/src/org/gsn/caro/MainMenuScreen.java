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

import java.util.Date;

import org.gsn.engine.Debug;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen implements Screen {
	OrthographicCamera guiCam;
	SpriteBatch batcher;
	Vector3 localPoint;
	Vector3 globalPoint;
	Sprite soundSprite; 
	Sprite castleSprite;
	Animation bob = Assets.bobFall;
	
	final float WIDTH = Assets.WIDTH;
	final float HEIGHT = Assets.HEIGHT;
	
	public MainMenuScreen (Game game) {
		
		soundSprite = new Sprite(Assets.soundOn);
		soundSprite.setPosition(2000, 2000);
		
		castleSprite = new Sprite(Assets.castle);
		castleSprite.setPosition(2000, 2000);
		
		guiCam = new OrthographicCamera(WIDTH, HEIGHT);
		guiCam.position.set(2000, 2000, 0);
		batcher = new SpriteBatch();		
		globalPoint = new Vector3();
		localPoint = new Vector3();
	}

	long begin = new Date().getTime();
	public void update (float deltaTime) {
		
		if (Gdx.input.justTouched()) {		
			localPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			guiCam.unproject(globalPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			Debug.trace("local : " + localPoint);
			Debug.trace("global: " + globalPoint);
//			Debug.trace("X Y original : " + soundSprite.getOriginX() + " "  + soundSprite.getOriginY());
//			Debug.trace("X Y region : " + soundSprite.getRegionX() + " "  + soundSprite.getRegionY());
//			Debug.trace("X Y float : " + soundSprite.getRegionX() + " "  + soundSprite.getRegionY());
//			Debug.trace(soundSprite.getBoundingRectangle());
			if (OverlapTester.pointInRectangle(soundSprite.getBoundingRectangle(), globalPoint.x, globalPoint.y)) {				
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
//		batcher.disableBlending();
//		batcher.begin();
//		batcher.draw(Assets.backgroundRegion, 0, 0, 320, 480);
//		batcher.end();

		
		
		batcher.setProjectionMatrix(guiCam.combined);


		batcher.enableBlending();
		batcher.begin();		
		Assets.font.draw(batcher, "trung", 2000, 2000);
		castleSprite.draw(batcher, 1f);		
		soundSprite.draw(batcher, 1f);
		//batcher.draw(bob.getKeyFrame(new Date().getTime() - begin, true), 50, 50);
		
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
