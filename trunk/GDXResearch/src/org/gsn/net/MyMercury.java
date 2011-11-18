package org.gsn.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.gsn.engine.Debug;

public class MyMercury implements Runnable {
	public interface IMercuryListener {
		void onReceived(String s);

		void onConnected();

		void onDisconnected();
	}

	private IMercuryListener listener;
	private String host;
	private int port;
	public MyMercury(String host, int port, IMercuryListener listener) {
		this.listener = listener;
		this.host = host;
		this.port = port;
	}	
	
	public void setListener(IMercuryListener listener){
		this.listener = listener;
	}

	private ByteBuffer buf = ByteBuffer.allocateDirect(1024);

	public SocketChannel createSocketChannel(String hostName, int port) throws IOException {
		// Create a non-blocking socket channel
		SocketChannel sChannel = SocketChannel.open();
		sChannel.configureBlocking(false);

		// Send a connection request to the server; this method is non-blocking
		sChannel.connect(new InetSocketAddress(hostName, port));
		return sChannel;
	}

	private Queue<String> queue = new LinkedBlockingQueue<String>();

	public void send(String s) {
		queue.add(s);
	}
		

	public void processSelectionKey(SelectionKey selKey) throws IOException {
		// Since the ready operations are cumulative,
		// need to check readiness for each operation
		if (selKey.isValid() && selKey.isConnectable()) {
			// Get channel with connection request
			SocketChannel sChannel = (SocketChannel) selKey.channel();

			boolean success = sChannel.finishConnect();
			if (!success) {
				// An error occurred; handle it
				// Unregister the channel with this selector
				selKey.cancel();
			} else {
				if (listener != null)
					listener.onConnected();
			}
		}
		if (selKey.isValid() && selKey.isReadable()) {
			// Get channel with bytes to read
			SocketChannel sChannel = (SocketChannel) selKey.channel();
			read(sChannel);
			// See Reading from a SocketChannel
		}
		if (selKey.isValid() && selKey.isWritable()) {
			// Get channel that's ready for more bytes
			SocketChannel sChannel = (SocketChannel) selKey.channel();
			if (!queue.isEmpty())
				write(sChannel);
			// See Writing to a SocketChannel
		}
	}

	private byte[] convertToBytes(String s){		
		try{			
			byte[] bytes = s.getBytes("UTF-8");
			ByteBuffer buffer = ByteBuffer.allocate(bytes.length + 2);
			buffer.putShort((short) bytes.length);
			buffer.put(bytes);
			buffer.flip();
			byte[] dst = new byte[buffer.remaining()];
			buffer.get(dst);
			return dst;
			
		} catch (UnsupportedEncodingException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	private void write(SocketChannel socketChannel) {
		// TODO Auto-generated method stub
		String s = queue.poll();
		if (s == null)
			return;
		try {
			// Fill the buffer with the bytes to write;
			// see Putting Bytes into a ByteBuffer
			byte[] b = convertToBytes(s);
			buf.put(b);
			// Prepare the buffer for reading by the socket
			buf.flip();
			// Write bytes
			int numBytesWritten = socketChannel.write(buf);
			buf.clear();			
		} catch (IOException e) {
			// Connection may have been closed
		}
	}

	private void read(SocketChannel socketChannel) {
		// TODO Auto-generated method stub
		try {
			// Clear the buffer and read bytes from socket
			buf.clear();
			int numBytesRead = socketChannel.read(buf);

			if (numBytesRead == -1) {
				// No more bytes can be read from the channel
				if (listener != null)
					listener.onDisconnected();
				socketChannel.close();
			} else {
				// To read the bytes, flip the buffer
				buf.flip();

				// Read the bytes from the buffer ...;
				// see Getting Bytes from a ByteBuffer
				byte[] dst = new byte[buf.remaining()];
				buf.get(dst);
				buf.clear();
				String s = new String(dst);			
				if (listener != null)
					listener.onReceived(s);
			}
		} catch (IOException e) {
			// Connection may have been closed
		}
	}

	public void run() {
		Selector selector = null;
		try {
			// Create the selector
			selector = Selector.open();
			// Creating a Non-Blocking Socket.
			SocketChannel sChannel = createSocketChannel(host, port);
			// Register the channel with selector, listening for all events
			sChannel.register(selector, sChannel.validOps());	
		} catch (IOException e) {
		}

		// Wait for events
		while (true) {
			try {
				// Wait for an event
				if (selector.select() == 0)
					continue;
			} catch (IOException e) {
				// Handle error with selector
				break;
			}

			// Get list of selection keys with pending events
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();

			// Process each key at a time
			while (it.hasNext()) {
				// Get the selection key
				SelectionKey selKey = it.next();

				// Remove it from the list to indicate that it is being
				// processed
				it.remove();

				try {
					processSelectionKey(selKey);
				} catch (IOException e) {
					// Handle error with channel and unregister
					selKey.cancel();
				}
			}
		}
	}

	public static MyMercury m;
	
	public static void main(String[] args) {
		System.out.println("chay...");
		m = new MyMercury("120.138.65.118", 443, new IMercuryListener() {

			@Override
			public void onReceived(String s) {
				// TODO Auto-generated method stub				
				System.out.println("receive: " + s);
				if (s.contains("loginOK")){
					String gui = "{\"params\":null,\"_cmd\":\"GUI\",\"ext\":\"caro\"}";
					m.send(gui);
				}
			}

			@Override
			public void onDisconnected() {
				// TODO Auto-generated method stub
				System.out.println("disconnect");
			}

			@Override
			public void onConnected() {
				// TODO Auto-generated method stub
				System.out.println("connect");
				String s = "{\"params\":{\"username\":\"1F018988F79F2897D5A40ED7\"},\"_cmd\":\"login\",\"ext\":\"caro\"}";
				m.send(s);
			}
		});
		Thread t = new Thread(m);		
		t.run();		
	}
}
