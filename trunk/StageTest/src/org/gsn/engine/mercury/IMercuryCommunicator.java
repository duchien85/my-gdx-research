package org.gsn.engine.mercury;

import org.json.JSONObject;

public interface IMercuryCommunicator{	
	void connect();
	void disconnect();
	void killService();
	void sendPacket(String s);
	void setSession(String s);
}
