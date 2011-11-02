package org.gsn.engine;

import java.net.InetAddress;
import java.nio.ByteBuffer;

import org.json.JSONObject;

public class MercuryClient {
	private Thread t;
	private NioClient client;
	private RspHandler handler;
	public MercuryClient(String host, int port) {
		// TODO Auto-generated constructor stub
		try{
		client = new NioClient(InetAddress.getByName(host), port);
		t = new Thread(client);
		handler = new RspHandler();
		t.setDaemon(true);						
		//handler.waitForResponse();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void disconect(){
		client.disconect();		
	}

	public void connect() {
		// TODO Auto-generated method stub
		t.start();
	}
	

	public void addListener(IMercuryListenter caroGame) {
		// TODO Auto-generated method stub
		handler.setListener(caroGame);
		
	}

	public void write(String s) {
		// TODO Auto-generated method stub
		Debug.trace("send : " + s);
		byte[] bytes;
		try {
			bytes = s.getBytes("UTF-8");
			ByteBuffer buffer = ByteBuffer.allocate(s.length() + 2);
			buffer.putShort((short) bytes.length);
			buffer.put(bytes);			
			client.send(buffer.array(), handler);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}								
	}

	public void write(JSONObject json) {
		// TODO Auto-generated method stub
		write(json.toString());
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
