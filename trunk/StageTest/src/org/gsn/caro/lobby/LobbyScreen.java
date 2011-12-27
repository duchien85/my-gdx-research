package org.gsn.caro.lobby;

//mport org.gsn.caro;
import java.util.Arrays;

import org.gsn.engine.Debug;
import org.gsn.engine.GSNScreen;
import org.gsn.engine.TwoStateButton;
import org.gsn.engine.Utility;
import org.gsn.game.CaroGame;
import org.gsn.game.CaroSettings;
import org.gsn.game.Constant;
import org.gsn.game.DataProvider;
import org.gsn.game.CaroGame.ScreenID;
import org.gsn.packet.PacketFactory;

import android.os.Looper;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class LobbyScreen extends GSNScreen {
	//SpriteBatch globalBatcher;	
	Image background;	
	GuiBet guiBet;
	TwoStateButton instantPlayButton;
	TextureRegion avatarLobby;
		
	//public CaroMenu lobbyMenu;
	//Stage stage;
	
	public LobbyScreen(CaroGame game) {
		super(game);
		guiBet = new GuiBet();
		guiBet.setLobby(this);
		setContent();		
	}	
	
	public void setContent(){
		background = createImage(getAssets().background,WIDTH,HEIGHT);		
		avatarLobby = getAssets().avatarLobby;
		instantPlayButton = new TwoStateButton(getAssets().choingayEnable, getAssets().choingayDisable,getAssets().choingayDown,127,35);
		instantPlayButton.setPosition( 10 + Constant.WIDTH/2-instantPlayButton.getWidth()/2, Constant.HEIGHT/2-instantPlayButton.getHeight()/2-15);				
		instantPlayButton.setEnable(false);
		
//		unregisterAllButtons();
//		registerButton(instantPlayButton);
		
//		lobbyMenu = new CaroMenu(CaroMenu.MENU_LOBBY, Arrays.asList(MenuButton.getExitButton(),MenuButton.getMusicMenuButton(),MenuButton.getSoundMenuButton()));
//		lobbyMenu.setPosition(WIDTH-lobbyMenu.getWidth(), HEIGHT-4);
//		lobbyMenu.setListener(game);
//		lobbyMenu.regButtons(this);
//		
		//stage = new Stage(320, 240, true);		
		//stage.addActor(lobbyMenu);
		//guiBet.y = -15;
		guiBet.setContent();
		guiBet.setLobby(this);
		//guiBet.registerButton(this);
		instantPlayButton.setEnable(guiBet.selectedIndex>=0);
		stage.addActor(background);
		stage.addActor(guiBet);
		stage.addActor(instantPlayButton);
	}
	
	@Override
	public void onSetScreen() {
		super.onSetScreen();
		//Debug.trace("Onsetscreen");
		//guiBet.updateAccount();		
		instantPlayButton.setEnable(guiBet.selectedIndex>=0);
		instantPlayButton.setEnable(true);
		//lobbyMenu.hideMenu();
	}
	
	@Override
	public void touchUp(float x, float y) {		
		//super.touchUp(x, y);
		if (isTouchIn(instantPlayButton, x, y)){
			Debug.trace("Instant play!");
		}
	}
//	@Override
//	public void oneTouchUp(int x, int y) {
//		localTouch.set(x, HEIGHT - y, 0);		
//		//if (lobbyMenu.touchUp(localTouch.x, localTouch.y)) return;
//		if (instantPlayButton.isTouchIn(localTouch.x, localTouch.y))
//		{
//			showEffect(instantPlayButton.getMiddleX(),instantPlayButton.getMiddleY());
//			if (instantPlayButton.isEnable()){
//				game.sendPacket(PacketFactory.createQuickPlay(guiBet.betValue,guiBet.betType).toString());
//				game.boardScreen.init();
//				game.boardScreen.setBet(guiBet.betValue, guiBet.betType);				
//				game.setScreen(ScreenID.BOARD);
//			}else
//			{				
//				notChooseBet();
//			}
//		}else		
//			guiBet.touchUp(localTouch.x, localTouch.y);
//		instantPlayButton.setEnable(guiBet.selectedIndex>=0);
//	}
//	private void notChooseBet() {		
//		game.dialogManager.openDialog(DialogManager.NOT_CHOOSE_BET, "ChÆ°a chá»�n má»©c cÆ°á»£c!", GSNDialog.DIAGLOG_OK);
//	}

	public void initBet(){
		Debug.trace("iNIT BET");
		guiBet.initBet();
		instantPlayButton.setEnable(guiBet.selectedIndex>=0);
	}
	
//	@Override
//	public void onRender(float delta) {		
//		GLCommon gl = Gdx.gl;
//		gl.glClearColor(0, 0, 0, 1);
//		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//		guiCam.update();		
//		localBatcher.begin();
//		background.draw(localBatcher);
//		localBatcher.enableBlending();		
//		localBatcher.draw(avatarLobby,10,10);
//		getAssets().font.draw(localBatcher, Utility.getShortName(DataProvider.getInstance().getName()), 18, 91);		
//		getAssets().font.draw(localBatcher, String.valueOf(DataProvider.getInstance().getExp()), 32, 70);
//		getAssets().font.draw(localBatcher, String.valueOf(DataProvider.getInstance().getGold()), 32, 58);
//		getAssets().font.draw(localBatcher, String.valueOf(DataProvider.getInstance().getXu()), 32, 45);
//		guiBet.updateAccount();		
//		guiBet.draw(localBatcher);		
//		lobbyMenu.draw(localBatcher, 1);		
//		instantPlayButton.draw(localBatcher);
//		getAssets().font.draw(localBatcher, CaroSettings.version+" fps: " + Gdx.graphics.getFramesPerSecond()+" P:"+ String.valueOf(game.pingTime), 115, 15);		
//		localBatcher.end();
//		//stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
//		//stage.draw();	
//	}

	@Override
	public boolean keyDown(int keycode) {
		super.keyDown(keycode);
		switch (keycode) {
		case Input.Keys.BACK:
		case Input.Keys.ESCAPE:
			Debug.trace("BACK");
			back();
			break;
		case Input.Keys.MENU:
			//lobbyMenu.toggleMenu();
			break;
		}
		return false;		
	}
	
	public void back(){
		//loobyMenu.hideMenu();
		//game.dialogManager.openDialog(DialogManager.EXIT_LOBBY, "ThoÃ¡t khá»�i lobby?", GSNDialog.DIAGLOG_OK_CANCEL);
	}

	public void notEnoughMoney() {
		//game.dialogManager.openDialog(DialogManager.NOT_ENOUGH_MONEY, "KhÃ´ng Ä‘á»§ tiá»�n!", GSNDialog.DIAGLOG_OK);		
	}
	
	
	
}
