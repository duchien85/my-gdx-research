package org.gsn.game;

import java.util.List;


import org.gsn.caro.Login.LoginScreen;
import org.gsn.caro.lobby.LobbyScreen;
import org.gsn.caro.userinfo.UserInfo;
import org.gsn.engine.Debug;
import org.gsn.engine.IMenuListener;

import org.gsn.engine.loading.LoadingScreen;

import org.gsn.engine.mercury.GsnServerInfo;
import org.gsn.engine.mercury.IMercuryCommunicator;
import org.gsn.engine.mercury.MyMercury;
import org.gsn.engine.mercury.Ping;
import org.gsn.engine.mercury.Ping.IPingListener;
import org.gsn.game.CaroSettings.RunMode;
import org.gsn.packet.CmdDefine;
import org.gsn.packet.PacketFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public class CaroGame extends Game implements IMenuListener{
	private String session;
	private AndroidApplication context;
	//public final static int PING_DELAY = 5;
	public boolean isConnect = false;
	public void setSession(String session) {
		((IMercuryCommunicator)context).setSession(session);
	}

	public AndroidApplication getContext() {
		return context;
	}

	public enum ScreenID {
		LOADING, LOGIN, LOBBY, BOARD, MENU, TEST
	}

	public LoadingScreen loadingScreen;
	public LoginScreen loginScreen;
	public LobbyScreen lobbyScreen;
//	public BoardScreen boardScreen;
	public ScreenID currentScreen = ScreenID.LOGIN;
	
	private boolean isLoad = true;
	public boolean canPlayGame= true;

//	public DialogManager dialogManager;

	public CaroGame(AndroidApplication app) {
		System.out.println("INIT Caro GAME");
		this.context = app;
	}
	
	boolean reset = false;
	
	public ParticleEffect pEffect;
	public void create() {
		pEffect = new ParticleEffect();
		pEffect.load(Gdx.files.internal("data/thu.p"), Gdx.files.internal("data"));
		pEffect.setPosition(100, 100);
		if (isLoad) {
			Debug.trace("Loading Screen");
			if (loadingScreen == null)
				loadingScreen = new LoadingScreen(this);
	//			Debug.trace("##########TEST SCREEN");
	//			testScreen = new TestScreen(this);
	//			setScreen(testScreen);
			setScreen(loadingScreen);
			isLoad = false;
		} else {
			CaroConfig.load();
			CaroAssets.newInstance().finishLoadingAll();
			initGame();
			Debug.trace("Setscreen : " + currentScreen.toString());
			setScreen(currentScreen);
//			loginScreen.setContent();
//			lobbyScreen.setContent();
//			boardScreen.setContent();
//			loginScreen.resetFinger();
//			boardScreen.resetFinger();
//			lobbyScreen.resetFinger();
		}
		CaroSettings.load();
		reset = true;
		DataProvider.getInstance().game = this;
		System.out.println("RUNMODE: " + CaroSettings.runMode.toString());
	}

	public void showEffect(float x, float y){
//		switch (currentScreen) {
//		case LOADING:
//			
//			break;
//		case LOGIN:
//			loginScreen.showEffect(x, y);
//			break;
//		case LOBBY:
//			lobbyScreen.showEffect(x, y);
//			break;
//		case BOARD:
//			boardScreen.showEffect(x, y);
//			break;				
//		}
	}
	
	public boolean loadFinish = false;
	@Override
	public void resume() {		
		Debug.trace("---------game resume");
		pEffect = new ParticleEffect();
		pEffect.load(Gdx.files.internal("data/thu.p"), Gdx.files.internal("data"));
		CaroAssets.getInstance().finishLoading();
		reset = false;
		if (!loadFinish) return;
		Debug.trace("---------game resume pass");
		if (loginScreen == null)
			loginScreen = new LoginScreen(this);
		if (lobbyScreen == null)
			lobbyScreen = new LobbyScreen(this);
//		if (boardScreen == null) 
//			boardScreen = new BoardScreen(this);
		
		loginScreen.setContent();
		lobbyScreen.setContent();
//		boardScreen.setContent();
//		loginScreen.resetFinger();
//		boardScreen.resetFinger();
//		lobbyScreen.resetFinger();
		DataProvider.getInstance().game = this;
	}

	public void pause() {
		Debug.trace("Game.pause");
	}
	
	public void initGame() {
		isConnect = false;
		if (CaroSettings.musicEnabled)
			CaroAssets.getInstance().music.play();
		//dialogManager = new DialogManager(this);
		if (loginScreen == null)
			loginScreen = new LoginScreen(this);
		if (lobbyScreen == null)
			lobbyScreen = new LobbyScreen(this);
//		if (boardScreen == null) {
//			boardScreen = new BoardScreen(this);
//			Debug.trace("############RELOAD BOEAD SCREEN");
//		}
//		if (CaroSettings.runMode == RunMode.TEST)
//			testScreen = new TestScreen(this);
	}

//	public boolean isHavingDialog() {
//		if (dialogManager == null)
//			return false;
//		else
//			return dialogManager.haveDialog();
//	}
//
//	public GSNDialog getDialog() {
//		return dialogManager.getCurrentDialog();
//	}
//
//	public DialogManager getDialogManager() {
//		return dialogManager;
//	}

	public boolean isNetWorkAvailable(){
		ConnectivityManager connectivityManager
			= (ConnectivityManager) this.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();		
		return activeNetworkInfo!=null;
	}
	
//	public void connectServer() {
//		try{
//			if (p!=null) p.stop();
//			if (client!=null) client.disconnect();			
//		
//		switch (CaroSettings.runMode) {
//		case TEST:
//			client = new MyMercury("10.198.48.118", 443, this);
//			p = new Ping("10.198.48.118", 443, this);
//			break;
//		case PRIVATE:
//			client = new MyMercury("120.138.65.118", 443, this);
//			p = new Ping("120.138.65.118", 443, this);
//			break;
//		case REAL:
//			List<GsnServerInfo> servers = null;
//			try {
//				servers = GsnServerInfo.getServerInfo(CaroConfig.HOST);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			GsnServerInfo se = servers.get(0);
//			client = new MyMercury(se.getIp(), se.getPort(), this);
//			p = new Ping(se.getIp(), se.getPort(), this);
//			break;
//		default:
//			break;
//		}
//		client.connect();
//		DataProvider.getInstance().setClient(client);
//		}catch (Exception e) {
//			Debug.trace("Cannot establish a connection to server");
//			e.printStackTrace();
//		}
//	}

	public void connectServer(){
		((IMercuryCommunicator)context).connect();
	}

	public void sendPacket(String s) {
		((IMercuryCommunicator)context).sendPacket(s);
	}
	
	public void disconnect() {
		((IMercuryCommunicator)context).disconnect();
	}

	public void killService() {
		((IMercuryCommunicator)context).killService();
	}
	
	public void setScreen(ScreenID id) {
		currentScreen = id;
		switch (id) {
		case LOADING:
			if (loadingScreen == null)
				loadingScreen = new LoadingScreen(this);
			setScreen(loadingScreen);
			// Gdx.input.setInputProcessor(loadingScreen);
			break;
		case LOGIN:
			if (loginScreen == null)
				loginScreen = new LoginScreen(this);
			setScreen(loginScreen);
			loginScreen.onSetScreen();
			Gdx.input.setInputProcessor(loginScreen.stage);
			break;
		case LOBBY:
			Debug.trace("set lobby screen");
			if (lobbyScreen == null)
				lobbyScreen = new LobbyScreen(this);
			setScreen(lobbyScreen);
			lobbyScreen.onSetScreen();
			Gdx.input.setInputProcessor(lobbyScreen.stage);
			break;
//		case BOARD:
//			if (boardScreen == null)
//				boardScreen = new BoardScreen(this);
//			// boardScreen.clearGame();
//			setScreen(boardScreen);
//			boardScreen.onSetScreen();
//			Gdx.input.setInputProcessor(boardScreen);
//			break;
//		case TEST:
//			if (testScreen == null)
//				testScreen = new TestScreen(this);
//			setScreen(testScreen);
//			Gdx.input.setInputProcessor(testScreen);
//			break;		
		}
	}
	

	public void processReceived(JSONObject json) {
		if (!isConnect) return;
		try {
			Debug.trace("### Game Receive: " + json.toString());
			if (json.has("loginOK")) {
				int login = json.getInt("loginOK");
				if (login == 0) {			
					Debug.trace("Login ok");
					String gui = "{\"params\":null,\"_cmd\":\"GUI\",\"ext\":\"caro\"}";
					//client.send(gui);
					sendPacket(gui);
				//	p.loopPing(PING_DELAY);
				} else {
					Debug.trace("Cannot login. Check session id!");
//					loginScreen.cannotLogin();
				}
				return;
			} else {
				String cmd = json.getString(org.gsn.packet.CmdDefine.CMD);
				JSONObject params = json.getJSONObject(CmdDefine.PARAMS);

				if (cmd.equals(CmdDefine.GET_USER_INFO)) {
					Debug.trace("GUI");
					JSONObject me = params.getJSONObject("me");
					DataProvider.getInstance().setMyInfo(new UserInfo(me));
					setScreen(ScreenID.LOBBY);					
//					lobbyScreen.initBet();
					
				} else if (cmd.equals(CmdDefine.EARN_MONEY)) {
					int gold = 0;
					int xu = 0;
					try{
						gold = params.getInt("gold");
						xu = params.getInt("xu");						
					}catch (Exception e) {						
					}
					DataProvider.getInstance().earnMoney(gold,xu);
//					lobbyScreen.initBet();
					String type = params.getString("type");
					Log.d("Caro Service", "daily login: "+ type);
					if (type.equals("daily_login")){
						int day = params.getInt("day");
						String pre ="";
						if (day == 1)  pre = "NgÃ y Ä‘áº§u tiÃªn.";
							else pre = "NgÃ y thá»© "+ String.valueOf(day)+".";
						if (gold>0){
//							dialogManager.openDialog(DialogManager.EARN_MONEY, pre+" Báº¡n Ä‘Æ°á»£c táº·ng "+String.valueOf(gold)+" gold!", GSNDialog.DIAGLOG_OK);
						}else
							if (xu>0){
//								dialogManager.openDialog(DialogManager.EARN_MONEY, pre+  " Báº¡n Ä‘Æ°á»£c táº·ng "+String.valueOf(xu)+" xu!", GSNDialog.DIAGLOG_OK);
							}
					}else{
						if (gold>0){
//							dialogManager.openDialog(DialogManager.EARN_MONEY, "Báº¡n vá»«a Ä‘Æ°á»£c táº·ng "+String.valueOf(gold)+" gold!", GSNDialog.DIAGLOG_OK);
						}else
							if (xu>0){
//								dialogManager.openDialog(DialogManager.EARN_MONEY,  "Báº¡n vá»«a Ä‘Æ°á»£c táº·ng "+String.valueOf(xu)+" xu!", GSNDialog.DIAGLOG_OK);
							}
					}
					
				}else if (cmd.equals(CmdDefine.JOIN_ROOM)) {
					Debug.trace("join room");
					JSONArray users = params.getJSONArray("users");
					JSONObject one = users.getJSONObject(0);
					JSONObject two = users.getJSONObject(1);
					UserInfo infoOne = new UserInfo(one);
					UserInfo infoTwo = new UserInfo(two);
					if (infoOne.uid == DataProvider.getInstance().getMyInfo().uid)
						DataProvider.getInstance().setOtherInfo(infoTwo);
					else
						DataProvider.getInstance().setOtherInfo(infoOne);
//					boardScreen.setJoinRoom(true);
				} else if (cmd.equals(CmdDefine.GAME_READY)) {
					int id = params.getInt("userId");
//					boardScreen.processGameReady(id);
				} else if (cmd.equals(CmdDefine.GAME_START)) {					
					int firstMove = params.getInt("whoseTurn");
//					boardScreen.startgame(firstMove);
					// TODO new game
				}else if (cmd.equals(CmdDefine.CHAT)) {					
					String msg = params.getString("message");
//					boardScreen.receiveChat(msg);
					
				} else if (cmd.equals(CmdDefine.CHESS_MOVE)) {
					int turn = params.getInt("whoseTurn");
					int cell = params.getInt("cell");
//					boardScreen.move(turn, cell);
				} else if (cmd.equals(CmdDefine.OUT_ROOM)) {
					if (currentScreen == ScreenID.BOARD){
//						dialogManager.openDialog(DialogManager.OPPONENT_OUT_ROOM, "Ä�á»‘i thá»§ cá»§a báº¡n Ä‘Ã£ thoÃ¡t!", GSNDialog.DIAGLOG_OK);
					}
				} else if (cmd.equals(CmdDefine.GAME_STOP)) {
					if (!params.has("winner")) {
						int can = params.getInt("canContinue");
//						boardScreen.draw(can);
						if (can == 1 && currentScreen == ScreenID.BOARD) {
//							dialogManager.openDialog(DialogManager.CAN_CONTINUE_OPPONENT_OUT, "Ä�á»‘i thá»§ cá»§a báº¡n Ä‘Ã£ thoÃ¡t!", GSNDialog.DIAGLOG_OK);
						}
					} else {
						int winner = params.getInt("winner");
						int can = params.getInt("canContinue");
						boolean isWin = false;
						if (winner == DataProvider.getInstance().getMyInfo().uid) {
//							boardScreen.win(can);
							isWin = true;
						} else {
//							boardScreen.lose(can);
						}
						if (can == 1 && currentScreen == ScreenID.BOARD) {
//							dialogManager.openDialog(DialogManager.CAN_CONTINUE_OPPONENT_OUT, "Ä�á»‘i thá»§ cá»§a báº¡n Ä‘Ã£ thoÃ¡t!", GSNDialog.DIAGLOG_OK);
						} else if (can == 2) {
//							if (isWin)
//								dialogManager.openDialog(DialogManager.CAN_CONTINUE_NOT_ENOUGH_MONEY, "Ä�á»‘i thá»§ cá»§a báº¡n Ä‘Ã£ háº¿t tiá»�n!", GSNDialog.DIAGLOG_OK);
//							else
//								dialogManager.openDialog(DialogManager.CAN_CONTINUE_NOT_ENOUGH_MONEY, "Báº¡n Ä‘Ã£ háº¿t tiá»�n!", GSNDialog.DIAGLOG_OK);
						}
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dispose() {
		Debug.trace("Caro GAme dispose");
//		dialogManager = null;
//		loginScreen = null;
//		lobbyScreen = null;
//		boardScreen = null;		
		CaroSettings.save();
		super.dispose();
	}

//	public void closeDialog(String dialogId, int index) {
//		if (dialogId == DialogManager.CAN_CONTINUE_NOT_ENOUGH_MONEY) {
//			setScreen(ScreenID.LOBBY);
//			dialogManager.clearDialog();
//		} else if (dialogId == DialogManager.CAN_CONTINUE_OPPONENT_OUT) {
//			setScreen(ScreenID.LOBBY);
//			dialogManager.clearDialog();
//		} else if (dialogId == DialogManager.OPPONENT_OUT_ROOM) {
//			setScreen(ScreenID.LOBBY);
//			dialogManager.clearDialog();
//		} else if (dialogId == DialogManager.BACK) {
//			if (index == 0){
//				//if (client != null)
//				//	client.disconnect();
//				//killService();
//				disconnect();
//				Gdx.app.exit();
//			}
//		} else if (dialogId == DialogManager.EXIT_ROOM) {
//			if (index == 0) {
//				sendPacket(PacketFactory.createOutRoom().toString());
//				setScreen(ScreenID.LOBBY);
//				dialogManager.clearDialog();
//			}
//		} else if (dialogId == DialogManager.EXIT_LOBBY) {
//			if (index == 0) {				
//				setScreen(ScreenID.LOGIN);
//				disconnect();
//				//p.stop();
//				dialogManager.clearDialog();
//			}
//		} else if (dialogId == DialogManager.DISCONNECTED) {
//			//if (client != null)
//			disconnect();			
//			setScreen(ScreenID.LOGIN);
//			dialogManager.clearDialog();
//		}else if (dialogId == DialogManager.DESTRIOYED) {			
//			//disconnect();			
//			Gdx.app.exit();
//		}
//	}

//	public void back() {
//		if (currentScreen == ScreenID.LOADING)
//			return;
//		dialogManager.openDialog(DialogManager.BACK, "Báº¡n cÃ³ thá»±c sá»± muá»‘n thoÃ¡t khÃ´ng?", GSNDialog.DIAGLOG_OK_CANCEL);
//	}
//
//	@Override
//	public void clickMenu(int menuId, int buttonId) {
//		switch (menuId) {
//		case CaroMenu.MENU_LOBBY:
//			if (buttonId == MenuButton.MENU_EXIT) {
////				lobbyScreen.lobbyMenu.hideMenu();
//				dialogManager.openDialog(DialogManager.EXIT_LOBBY, "ThoÃ¡t khá»�i Lobby?", GSNDialog.DIAGLOG_OK_CANCEL);
//			}
//			break;
////		case CaroMenu.MENU_BOARD:
////			if (buttonId == MenuButton.MENU_EXIT) {			
////				boardScreen.boardMenu.hideMenu();
////				dialogManager.openDialog(DialogManager.EXIT_ROOM, "ThoÃ¡t khá»�i bÃ n chÆ¡i?", GSNDialog.DIAGLOG_OK_CANCEL);
////			} else if (buttonId == MenuButton.MENU_RESET) {
////				boardScreen.resetCamera();
////				boardScreen.boardMenu.hideMenu();
////			}
////			break;
//		}
//	}

	public void onConnected() {
		// TODO Auto-generated method stub
		String s = "{\"params\":{\"username\":\"" + session + "\"},\"_cmd\":\"login\",\"ext\":\"caro\"}";
		sendPacket(s);
	}

	
	public void onDisconnected() {
//		//if (p!=null) p.stop();
//		if (currentScreen == ScreenID.LOGIN){
//			dialogManager.openDialog(DialogManager.NO_INTERNET, "KhÃ´ng thá»ƒ káº¿t ná»‘i tá»›i tráº¡i cÃº. Vui lÃ²ng kiá»ƒm tra láº¡i káº¿t ná»‘i internet cá»§a báº¡n.", GSNDialog.DIAGLOG_OK);
//		}else
//		dialogManager.openDialog(DialogManager.DISCONNECTED, "Máº¥t káº¿t ná»‘i tá»›i tráº¡i cÃº!", GSNDialog.DIAGLOG_OK);
	}

	
	public void onReceived(String s) {
		String[] arr = s.split("\0");
		for (int i = 0; i < arr.length; i++) {
			arr[i].trim();
			try {
				if (arr[i].length() > 0){
					JSONObject json = new JSONObject(arr[i]);
					processReceived(json);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
//
	public long pingTime=0;


	public void onDestroyed() {
//		dialogManager.openDialog(DialogManager.DESTRIOYED, "Lá»—i há»‡ thá»‘ng!", GSNDialog.DIAGLOG_OK);		
	}

	@Override
	public void clickMenu(int menuId, int buttonId) {
		// TODO Auto-generated method stub
		
	}

}
