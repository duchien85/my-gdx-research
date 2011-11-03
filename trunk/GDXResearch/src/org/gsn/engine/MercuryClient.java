package org.gsn.engine;

import java.net.InetAddress;
import java.nio.ByteBuffer;

import org.json.JSONObject;

public class MercuryClient {
	
	
	
	
	public void disconect(){
			
	}

	public void connect() {
		// TODO Auto-generated method stub
	
	}
	

	public void addListener(IMercuryListenter caroGame) {
		// TODO Auto-generated method stub
	
		
	}

	public void write(String s) {
		// TODO Auto-generated method stub
									
	}

	public void write(JSONObject json) {
		// TODO Auto-generated method stub
	
	}
	
	public static void main1(String[] args){
		try {			
			NioClient client = new NioClient(InetAddress.getByName("www.google.com"), 80);
			Thread t = new Thread(client);
			//t.setDaemon(true);
			t.start();
			RspHandler handler = new RspHandler();
			client.send("GET / HTTP/1.0\r\n\r\n".getBytes(), handler);
			//handler.waitForResponse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
