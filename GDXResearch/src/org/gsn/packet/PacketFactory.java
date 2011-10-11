package org.gsn.packet;

import org.json.JSONException;
import org.json.JSONObject;

public  class PacketFactory {
	private static JSONObject create(){
		JSONObject json = new JSONObject();
		try {
			json.put("ext", "caro");			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return json;
	}
	
	public static JSONObject createQuickPlay(int betLv){
		//100, 500, 5000
		try {
			JSONObject json = create().put(CmdDefine.CMD, CmdDefine.INSTANCE_PLAY);
			JSONObject params = new JSONObject().put("active", true);
			params.put("type", "gold");
			params.put("betLv", betLv);
			json.put(CmdDefine.PARAMS, params);
			return json;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}				
	}
	
	public static JSONObject createReady(){
		try {
			return create().put(CmdDefine.CMD, CmdDefine.GAME_READY);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject createMove(int turn, int cell){
		JSONObject json = create();
		JSONObject params = new JSONObject();
		try {
			params.put("whoseTurn", turn);
			params.put("cell", cell);
			json.put(CmdDefine.CMD, CmdDefine.CHESS_MOVE);
			json.put(CmdDefine.PARAMS, params);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	public static JSONObject createOutRoom(){
		try {
			return create().put(CmdDefine.CMD, CmdDefine.OUT_ROOM);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
}
