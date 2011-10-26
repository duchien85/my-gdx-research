package org.gsn.userinfo;

import org.omg.CORBA.FREE_MEM;

public class UserInfo {
	
	enum State {
		IN_FREE, IN_READY, IN_PLAY
	}	
	public long id;
	public String username;
	public String avatarUrl;
	public int numWin;
	public int numLose;
	public int exp;
	public long gold;
	public long coin;
	public State state;
	
	public UserInfo()
	{
		state = IN_FREE;	
	}
	
}
