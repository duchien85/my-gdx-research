package org.gsn.game;

import org.gsn.caro.userinfo.UserInfo;
import org.gsn.engine.Debug;
import org.gsn.engine.mercury.MercuryClient;
import org.gsn.engine.mercury.MyMercury;

public class DataProvider {
	private static DataProvider _instance;
	public CaroGame game;
	
	public static DataProvider getInstance() {
		if (_instance == null)
			_instance = new DataProvider();
		return _instance;
	}

	private UserInfo myInfo;
	private UserInfo otherInfo;
	
	public void solveWin (int winID, int betLevel, int betType){		
		// Tien
		if (betType == Constant.BET_GOLD) {
			if (winID == getMyInfo().uid){
				myInfo.gold+=betLevel;
				otherInfo.gold -= betLevel;
				
			}else
			{
				myInfo.gold -= betLevel;
				otherInfo.gold += betLevel;
			}
			Debug.trace("Update gold: "+myInfo.gold+" "+otherInfo.gold);
			
		} else{
			if (winID == getMyInfo().uid){
				myInfo.xu +=betLevel;
				otherInfo.xu -= betLevel;
				
			}else
			{
				myInfo.xu -= betLevel;
				otherInfo.xu += betLevel;
			}
		}
		// Exp
		if (winID == getMyInfo().uid){
			int p = caculateEXPPoint(myInfo.exp, otherInfo.exp); 
			myInfo.exp+=p;
			otherInfo.exp-=p;
		}else
		{
			int p = caculateEXPPoint(otherInfo.exp,myInfo.exp); 
			myInfo.exp-=p;
			otherInfo.exp+=p;
		}
	}
	
    private int caculateEXPPoint(int X, int Y) {
        float Hieu = (X - Y);
        int max = Math.max(0, 16 - Math.round(Hieu / 16));
        return Math.min(32, max);
    }

	public UserInfo getMyInfo() {
		return myInfo;
	}

	public void setMyInfo(UserInfo myInfo) {
		this.myInfo = myInfo;
	}

	public UserInfo getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(UserInfo otherInfo) {
		this.otherInfo = otherInfo;
	}

	private MyMercury client;

	public void setClient(MyMercury client) {
		// TODO Auto-generated method stub
		this.client = client;
	}

	public MyMercury getClient() {
		return client;
	}
	
	public int getGold(){
		if (myInfo!=null) return myInfo.gold;
		else return 0;
	}
	public int getXu(){
		if (myInfo!=null) return myInfo.xu;
		else return 0;
	}
	public String getName(){
		if (myInfo!=null) return myInfo.name;
		else return "name";
	}
	
	public int getExp(){
		if (myInfo!=null) return myInfo.exp;
		else return 0;
	}

	public void earnMoney(int gold, int xu) {
		myInfo.gold+=gold;
		myInfo.xu+=xu;		
	}
}
