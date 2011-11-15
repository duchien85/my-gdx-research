package org.gsn.caro;

import org.gsn.engine.Debug;
import org.gsn.engine.IMercuryListenter;
import org.gsn.engine.MyMercuryClient;
import org.gsn.packet.CmdDefine;
import org.gsn.packet.PacketFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class CaroGame extends Game implements IMercuryListenter {
	final static int TEST = -1;
	final static int LOBBY = 0;
	final static int BOARD = 1;
	private LobyScreen lobbyScreen;
	private BoardScreen boardScreen;
	private TestScreen testScreen;
	MyMercuryClient client;	
	CaroAssetManager manager; 
	@Override
	public void create() {	
		Debug.trace("CREATE");
		manager = CaroAssetManager.newInstance();		
		Constant.load();
		setScreen(TEST);
		//connect();
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		Debug.trace("PAUSE");		
		super.pause();
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Debug.trace("DISPOSE");
		super.dispose();
	}
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		Debug.trace("RESUME");		
		super.resume();		
	}

	private void connect() {
		client = new MyMercuryClient("120.138.65.104", 443);
		client.connect();
		
		client.addListener(this);
		client.addListener(this);

		String s = "{\"params\":{\"username\":\"1F018988A3DD2E829542684D\"},\"_cmd\":\"login\",\"ext\":\"caro\"}";
		client.write(s);
	}

	public void setScreen(int id) {
		switch (id) {
		case LOBBY:
			if (lobbyScreen == null)
				lobbyScreen = new LobyScreen(this);
			setScreen(lobbyScreen);
			Gdx.input.setInputProcessor(lobbyScreen);
			break;
		case BOARD:
			if (boardScreen == null)
				boardScreen = new BoardScreen(this);
			boardScreen.clearGame();
			setScreen(boardScreen);
			Gdx.input.setInputProcessor(boardScreen);
			break;
		case TEST:
			if (testScreen == null)
				testScreen = new TestScreen();
			setScreen(testScreen);
			Gdx.input.setInputProcessor(testScreen);
			break;
		}
	}

	public void processData(String receive) {
		try {
			Debug.trace("JSON receive: " + receive);
			JSONObject json = new JSONObject(receive);
			if (json.has("loginOK")) {
				int login = json.getInt("loginOK");
				if (login == 0) {
					// login thanh cong.
					// gui GUI len
					String gui = "{\"params\":null,\"_cmd\":\"GUI\",\"ext\":\"caro\"}";
					client.write(gui);
				} else {
					Debug.trace("Ko login dc");
				}
				return;
			} else {

				String cmd = json.getString(org.gsn.packet.CmdDefine.CMD);
				JSONObject params = json.getJSONObject(CmdDefine.PARAMS);

				if (cmd.equals(CmdDefine.GET_USER_INFO)) {
					Debug.trace("GUI");
					JSONObject me = params.getJSONObject("me");
					DataProvider.getInstance().setMyInfo(new UserInfo(me));
				} else if (cmd.equals(CmdDefine.JOIN_ROOM)) {
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
					client.write(PacketFactory.createReady());
				} else if (cmd.equals(CmdDefine.GAME_START)) {
					// Toast.makeText(this, "Game Start", Toast.LENGTH_LONG);
					Debug.trace("start");
					int firstMove = params.getInt("whoseTurn");
					// TODO new game
				} else if (cmd.equals(CmdDefine.CHESS_MOVE)) {
					int turn = params.getInt("whoseTurn");
					int cell = params.getInt("cell");
					// TODO move
				} else if (cmd.equals(CmdDefine.GAME_STOP)) {
					if (!params.has("winner")) {
						// TODO hoa

					} else {
						// TODO thang thua
						int winner = params.getInt("winner");
						int can = params.getInt("canContinue");
						if (winner == DataProvider.getInstance().getMyInfo().uid) {
							// win
						} else {
							// lose
						}
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public void onReceive(String receive) {

		String[] arr = receive.split("\0");
		for (int i = 0; i < arr.length; i++) {
			arr[i].trim();			
			if (arr[i].length() > 0)
				processData(arr[i]);
		}
	}
}
