package org.gsn.caro;

import org.json.JSONException;
import org.json.JSONObject;

//{"exp":1623,"numLose":65,"numWin":76,"username":"trungdv2","userId":"24676458","gold":24
//050,"avatar":"http://avatar.me.zdn.vn/avatar_files/6/0/4/0/trungdv2_160_2.jpg","xu":4}}
public class UserInfo {
	public int exp;
	public int numLose;
	public int numWin;
	public int uid;
	public int gold;
	public int xu;
	public String name;
	public String avatar;

	public UserInfo(JSONObject data) {
		try {
			exp = data.getInt("exp");
			numLose = data.getInt("numLose");
			numWin = data.getInt("numWin");
			uid = data.getInt("userId");
			gold = data.getInt("gold");
			xu = data.getInt("xu");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
