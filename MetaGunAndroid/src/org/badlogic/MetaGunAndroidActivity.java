package org.badlogic;

import java.net.InetAddress;

import org.gsn.caro.CaroGame;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class MetaGunAndroidActivity extends AndroidApplication {

	public void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			NioClient client = new NioClient(InetAddress.getByName("www.google.com"), 80);
			Thread t = new Thread(client);
			t.setDaemon(true);
			t.start();
			RspHandler handler = new RspHandler();
			client.send("GET / HTTP/1.0\r\n\r\n".getBytes(), handler);
			handler.waitForResponse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		initialize(new CaroGame(), false);
	}
}