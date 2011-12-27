package org.gsn.caro.Login;

//import org.gsn.caro.getAssets();
import java.util.Timer;

import org.gsn.engine.Debug;
import org.gsn.engine.GSNScreen;
import org.gsn.engine.Utility;

import org.gsn.game.CaroAssetManager;
import org.gsn.game.CaroAssets;
import org.gsn.game.CaroGame;
import org.gsn.game.CaroGame.ScreenID;
import org.gsn.game.CaroSettings;
import org.gsn.game.CaroSettings.RunMode;

import vn.vng.sdk.LoginDialog.IDialogDelegate;
import vn.vng.sdk.LoginDispatcher;
import vn.vng.sdk.session.SessionContext;
import vn.vng.sdk.session.SessionContext.ISessionContextDelegate;
import vn.vng.sdk.throwable.DialogThrowable;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;


public class LoginScreen extends GSNScreen {
	String NEHE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz\n1234567890" //
			+ "\"!`?'.,;:()[]{}<>|/@\\^$-%+=#_&~*"
			+ "ÃƒÂ¡ÃƒÂ Ã¡ÂºÂ£ÃƒÂ£Ã¡ÂºÂ¡" + "ÃƒÂ¢Ã¡ÂºÂ¥Ã¡ÂºÂ§Ã¡ÂºÂ©Ã¡ÂºÂ«Ã¡ÂºÂ­" + "Ã„Æ’Ã¡ÂºÂ¯Ã¡ÂºÂ±Ã¡ÂºÂ³Ã¡ÂºÂµÃ¡ÂºÂ·" + "ÃƒÂ©ÃƒÂ¨Ã¡ÂºÂ»Ã¡ÂºÂ½Ã¡ÂºÂ¹" + "ÃƒÂªÃ¡ÂºÂ¿Ã¡Â»ï¿½Ã¡Â»Æ’Ã¡Â»â€¦Ã¡Â»â€¡" + "iÃƒÂ­ÃƒÂ¬Ã¡Â»â€°Ã„Â©Ã¡Â»â€¹" + "oÃƒÂ³ÃƒÂ²Ã¡Â»ï¿½ÃƒÂµÃ¡Â»ï¿½" + "ÃƒÂ´Ã¡Â»â€˜Ã¡Â»â€œÃ¡Â»â€¢Ã¡Â»â€”Ã¡Â»â„¢" + "Ã†Â¡Ã¡Â»â€ºÃ¡Â»ï¿½Ã¡Â»Å¸Ã¡Â»Â¡Ã¡Â»Â£" + "uÃƒÂºÃƒÂ¹Ã¡Â»Â§Ã…Â©Ã¡Â»Â¥" + "Ã†Â°Ã¡Â»Â©Ã¡Â»Â«Ã¡Â»Â­Ã¡Â»Â¯Ã¡Â»Â±" + "yÃƒÂ½Ã¡Â»Â³Ã¡Â»Â·Ã¡Â»Â¹Ã¡Â»Âµ" + "Ãƒï¿½Ãƒâ‚¬Ã¡ÂºÂ¢ÃƒÆ’Ã¡ÂºÂ " + "Ãƒâ€šÃ¡ÂºÂ¤Ã¡ÂºÂ¦Ã¡ÂºÂ¨Ã¡ÂºÂªÃ¡ÂºÂ¬" + "Ã„â€šÃ¡ÂºÂ®Ã¡ÂºÂ°Ã¡ÂºÂ²Ã¡ÂºÂ´Ã¡ÂºÂ¶" + "Ãƒâ€°ÃƒË†Ã¡ÂºÂºÃ¡ÂºÂ¼Ã¡ÂºÂ¸" + "ÃƒÅ Ã¡ÂºÂ¾Ã¡Â»â‚¬Ã¡Â»â€šÃ¡Â»â€žÃ¡Â»â€ " + "IÃƒï¿½ÃƒÅ’Ã¡Â»Ë†Ã„Â¨Ã¡Â»Å " + "OÃƒâ€œÃƒâ€™Ã¡Â»Å½Ãƒâ€¢Ã¡Â»Å’" + "Ãƒâ€�Ã¡Â»ï¿½Ã¡Â»â€™Ã¡Â»â€�Ã¡Â»â€“Ã¡Â»Ëœ" + "Ã†Â Ã¡Â»Å¡Ã¡Â»Å“Ã¡Â»Å¾Ã¡Â»Â Ã¡Â»Â¢" + "UÃƒÅ¡Ãƒâ„¢Ã¡Â»Â¦Ã…Â¨Ã¡Â»Â¤" + "Ã†Â¯Ã¡Â»Â¨Ã¡Â»ÂªÃ¡Â»Â¬Ã¡Â»Â®Ã¡Â»Â°" + "YÃƒï¿½Ã¡Â»Â²Ã¡Â»Â¶Ã¡Â»Â¸Ã¡Â»Â´";
	SpriteBatch globalBatcher;
	private ImageButton loginButton;
	private ImageButton logoutButton;
	private ImageButton testButton;
	// private Sprite back;
	private ImageButton enterGameButton;
	Image background;

	private LoginDispatcher loginDispatcher = new LoginDispatcher();
	private ISessionContextDelegate delegate = new SessionContextDelegate();
	private static final String kApiKey = "8a489d0a9cda044bc6b20d1bc77a8360";
	private static final String kSecretKey = "f14061f7960e3422f72d7890a6612f1b";
	
	public boolean enableLogin;
	
	public boolean isLogingin = false;
	public float loginTime = 0;
	public static final float MAX_LOGIN_TIME = 5;
	private float time = 0;
	
		
	public LoginScreen(CaroGame game) {
		super(game);
		setContent();
		enableLogin = true;
		globalBatcher = new SpriteBatch();
		SessionContext.getSingletonSessionContext().beginSessionContextForApplication(kApiKey, kSecretKey);
		SessionContext.getSingletonSessionContext().addSessionContextDelegate(delegate);
		if (game.getContext() != null)
			SessionContext.getSingletonSessionContext().restore(game.getContext());
	}

	@Override
	public void onSetScreen() {	
		super.onSetScreen();
		isLogingin = false;
		game.isConnect  = false;
	}
	
	public void setContent(){		
		loginButton = createButton(getAssets().loginButton,getAssets().loginButtonDown,150,50);
		logoutButton = createButton(getAssets().logoutButton,getAssets().logoutButtonDown,90,30);		
		enterGameButton = new ImageButton(getAssets().enterGameButton,getAssets().enterGameButtonDown);
		moveTo(loginButton, WIDTH / 2 - loginButton.width/2, HEIGHT / 2 - loginButton.height / 2);
		moveTo(logoutButton, WIDTH - logoutButton.getPrefWidth()-5,5);				
		enterGameButton.x = WIDTH / 2 - enterGameButton.getPrefWidth() / 2;
		enterGameButton.y = HEIGHT / 2 - enterGameButton.getPrefHeight() + 20;		
		testButton = new ImageButton(getAssets().dialogButton);		
		background = createImage(getAssets().background,WIDTH,HEIGHT);		
		
		stage.addActor(background);
		stage.addActor(loginButton);
		stage.addActor(logoutButton);
	}
		
	@Override
	public void render(float delta) {	
		super.render(delta);
	/*	
		time += delta;
		GLCommon gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		localBatcher.begin();
		background.draw(localBatcher);
		localBatcher.enableBlending();
		//enableLogin = false;
		
		if (isLogingin){
				Debug.trace("Time: "+time);
				if (time - loginTime >=MAX_LOGIN_TIME+2){
					isLogingin = false;
					enableLogin = true;
				}else
				if (time - loginTime >=MAX_LOGIN_TIME){					
					getAssets().font.draw(localBatcher, "Lá»—i káº¿t ná»‘i! ", 130, 30);					
					game.isConnect  = false;
				}else
				if (time - loginTime < MAX_LOGIN_TIME){					
					getAssets().font.draw(localBatcher, "Ä�ang káº¿t ná»‘i...", 130, 30);					
				}
		}else{
			if (enableLogin)
				loginButton.draw(localBatcher);
			else {
				logoutButton.draw(localBatcher);
				enterGameButton.draw(localBatcher);			
			}
			if (CaroSettings.runMode==RunMode.TEST)
				testButton.draw(localBatcher);
		}
		getAssets().font.draw(localBatcher, CaroSettings.version+" fps: " + Gdx.graphics.getFramesPerSecond(), 130, 15);
		localBatcher.end();*/
	}

	
	public void touchUp(float x, float y) {		
		//if (loginButton.hit(x, y) != null){
		if (isTouchIn(loginButton, x, y)){
			Debug.trace("Login");
			game.setScreen(ScreenID.LOBBY);
		}else
			if (isTouchIn(logoutButton, x, y)){
				Debug.trace("Logout");
			}
	}
	

	private void openLoginDialog() {
		Debug.trace("Login enter");		
		Thread looperThread = new Thread() {
			public void run() {
				Looper.prepare();
				loginDispatcher.authorize(game.getContext(), new LoginDialogDelegate());
				Looper.loop();
			}
		};
		looperThread.start();
	}

	private final class SessionContextDelegate implements ISessionContextDelegate {

		public void onLoggedIn() {
			Log.d("Login delegate",SessionContext.getSingletonSessionContext().getSessionKey());
			enableLogin = false;
			game.setSession(SessionContext.getSingletonSessionContext().getSessionKey());
			Debug.trace("Logged in1");
			isLogingin = false;
		}

		public void onLoggedOut() {
			// chua dang nhap tu truoc
			Debug.trace("Logged out");
			enableLogin = true;
			isLogingin = false;
		}
	}

	private final class LoginDialogDelegate implements IDialogDelegate {

		public void onDialogComplete(Bundle values) {			
			SessionContext.getSingletonSessionContext().save(game.getContext());
			game.setSession(SessionContext.getSingletonSessionContext().getSessionKey());			
			enableLogin = false;
			isLogingin = false;
		}

		public void onDialogCancel() {
			// TODO Auto-generated method stub
			// loginButtonDelegate.onLoginCanceled();
			//Debug.trace("Login cancel");
			enableLogin = true;
			isLogingin = false;
		}

		public void onDialogThrowable(DialogThrowable dialogThrowable) {
			// TODO Auto-generated method stub
			// loginButtonDelegate.onLoginFailed();
			//Debug.trace("Login failed");
			enableLogin = true;
			isLogingin = false;
		}
	}


	public boolean keyDown(int keycode) {
		//super.keyDown(keycode);
		switch (keycode) {
		case Input.Keys.BACK:
		case Input.Keys.ESCAPE:
			Debug.trace("BACK");
			//game.back();
			break;
		}
		return false;
	}

	public void cannotLogin() {
		if (game.getContext() != null)
			SessionContext.getSingletonSessionContext().clear(game.getContext());
		else
			SessionContext.getSingletonSessionContext().setSessionKey(null);
		enableLogin = true;
	}

}
