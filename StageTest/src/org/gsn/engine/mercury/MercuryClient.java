package org.gsn.engine.mercury;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.gsn.engine.Debug;
import org.json.JSONObject;

public class MercuryClient {
	public MercuryClient(String server, int port) {
		this.server = server;
		this.port = port;
		listenerSet = new HashSet<IMercuryListenter>();
	}

	private String server;
	private int port;
	private Socket socket;
	private boolean connected;
	private Set<IMercuryListenter> listenerSet;

	// public void addListener(IMercuryListenter lis )

	public void write(JSONObject s) {
		write(s.toString());
	}

	public void write(String s) {
		try {
			Debug.trace("----- send : " + s);
			byte[] bytes = s.getBytes("UTF-8");
			ByteBuffer buffer = ByteBuffer.allocate(s.length() + 2);
			buffer.putShort((short) bytes.length);
			buffer.put(bytes);
			socket.getOutputStream().write(buffer.array());
			socket.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeAllListener() {
		listenerSet.clear();
	}

	public void addListener(IMercuryListenter l) {
		listenerSet.add(l);
	}

	public void removeListener(IMercuryListenter l) {
		listenerSet.remove(l);
	}

	public void connect() {
		Thread t = new Thread(new MyThread());
		t.start();
	}

	public void disconnect() {
		connected = false;
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isConnected() {
		if (socket == null)
			return false;
		return (socket.isConnected());
	}

	public void waitConnect() {
		while (!isConnected())
			;
	}

	private final String terminate = "\0";

	class MyThread implements Runnable {
		@Override
		public void run() {
			try {
				connected = true;
				InetAddress serverAddr = InetAddress.getByName(server);
				socket = new Socket(serverAddr, port);

				// String s =
				// "{\"params\":{\"username\":\"1F01898806543ED2AAC9A104\"},\"_cmd\":\"login\",\"ext\":\"caro\"}";
				// where you issue the commands
				char[] b = new char[1000];
				BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while (connected) {
					int n = read.read(b);
					if (n > 0) {
						String str = new String(b, 0, n);
						String[] arr = str.split(terminate);
						for (int i = 0; i < arr.length; i++) {
							Debug.trace("receive : *" + arr[i] + "*");
							Iterator<IMercuryListenter> it = listenerSet.iterator();
							while (it.hasNext()) {
								try {
									IMercuryListenter l = it.next();
									l.onReceive(arr[i]);
								} catch (Exception e) {
									Debug.e(e);
								}
							}
						}
					}
				}
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
