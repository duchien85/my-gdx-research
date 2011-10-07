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

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen implements Screen {
	OrthographicCamera guiCam;
	SpriteBatch batcher;

	Rectangle helpBounds;
	Vector3 touchPoint;
	Game game;
	Sprite soundSprite = new Sprite(Assets.soundOn);
	public MainMenuScreen (Game game) {
		this.game = game;
		guiCam = new OrthographicCamera(320, 480);		
		//guiCam.position.set(320 / 2, 480 / 2, 0);
		guiCam.position.set(0, 0, 0);
		batcher = new SpriteBatch();				//
		helpBounds = new Rectangle(160 - 150, 200 - 18 - 36, 300, 36);
		touchPoint = new Vector3();
		
		System.out.println(soundSprite.getBoundingRectangle());
		soundSprite.setPosition(0, 0);
		//soundSprite.setScale(2, 3);
		//soundSprite.setRotation(90);
		System.out.println(soundSprite.getBoundingRectangle());
	}
	
	public void update (float deltaTime) {		
		if (Gdx.input.justTouched()) {
			Vector2 globalTouch = new Vector2((float)Gdx.input.getX(), (float)Gdx.input.getY());
			System.out.println(" projected clicked :" + Gdx.input.getX() + " " + Gdx.input.getY());
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			System.out.println(" unprojected clicked:" +touchPoint.x + " " + touchPoint.y);
			if (OverlapTester.pointInRectangle(helpBounds, touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				
				return;
			}
			if (OverlapTester.pointInRectangle(soundSprite.getBoundingRectangle(), touchPoint.x, touchPoint.y)) {
				System.out.println("unproject click sound Sprite");
				Assets.playSound(Assets.clickSound);
				//game.setScreen(new HelpScreen(game));
			}
			
			if (OverlapTester.pointInRectangle(soundSprite.getBoundingRectangle(), globalTouch)) {
				System.out.println("project click sound Sprite");
				Assets.playSound(Assets.clickSound);
				//game.setScreen(new HelpScreen(game));
			}
		}
	}


	@Override
	public void render(float delta)  {
		//System.out.println("present");
		update(delta);
		GLCommon gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
		

//		batcher.disableBlending();
//		batcher.begin();
//		batcher.draw(Assets.backgroundRegion, 0, 0, 320, 480);
//		batcher.end();

		batcher.enableBlending();
		batcher.begin();
		Vector3 pos = new Vector3(160,  240, 0);
		guiCam.unproject(pos);
		soundSprite.setPosition(pos.x, pos.y);				
		//System.out.println(" new Position: " + pos.x + " " + pos.y);
		soundSprite.draw(batcher);		
		//batcher.draw(Assets.logo, 160 - 274 / 2, 480 - 10 - 142, 274, 142);
		//batcher.draw(Assets.mainMenu, 10, (int)(200 - 110 / 2), 300, 110);
		batcher.end();
	}

	@Override
	public void pause () {
		//Settings.save();
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
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
}
