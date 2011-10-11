package org.gsn.caro;

import org.gsn.engine.MercuryClient;


public class DataProvider {
	private static DataProvider _instance;
	public static DataProvider getInstance(){
		if (_instance == null)
			_instance = new DataProvider();
		return _instance;
	}
	
	private UserInfo myInfo;
	private UserInfo otherInfo;	
	
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

	private MercuryClient client;
	public void setClient(MercuryClient client) {
		// TODO Auto-generated method stub
		this.client = client;
	}
	public MercuryClient getClient(){
		return client;
	}
}
