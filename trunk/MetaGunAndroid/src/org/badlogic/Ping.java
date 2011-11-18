package org.badlogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Ping {
	public interface IPingListener {
		void onPing(long time);
	}

	private IPingListener listener;
	private String host;
	private int port;
	private byte[] pingByte;

	public Ping(String host, int port, IPingListener listener) {
		this.listener = listener;
		this.host = host;
		this.port = port;
		ByteBuffer pingBuf = ByteBuffer.allocate(2);
		pingBuf.clear();
		pingBuf.putShort((short) 65535);
		pingBuf.flip();
		pingByte = pingBuf.array();
	}

	private long ping() {
		long time = System.currentTimeMillis();
		Socket echoSocket = null;
		long kq = -1;
		try {
			echoSocket = new Socket(host, port);
			echoSocket.getOutputStream().write(pingByte);
			byte[] b = new byte[256];
			int num = echoSocket.getInputStream().read(b);
			if (num > 0) {
				System.out.println(num);
				kq = (System.currentTimeMillis() - time);
			} else
				kq = -1;
			echoSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (listener != null)
			listener.onPing(kq);
		return kq;
	}

	private static ScheduledExecutorService sche = Executors.newScheduledThreadPool(2);
	private ScheduledFuture<?> future = null;
	
	public void stop(){
		future.cancel(true);		
	}
	
	public void loopPing(){		
		future = sche.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				ping();
				// TODO Auto-generated catch block
			}
		}, 0, 1, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) throws InterruptedException {
		Ping p = new Ping("120.138.65.118", 443, new IPingListener() {
			
			@Override
			public void onPing(long time) {
				// TODO Auto-generated method stub
				System.out.println("ping : " + time);
			}
		});
		p.loopPing();		
	}
}
