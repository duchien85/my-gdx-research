package org.gsn.engine;


import org.gsn.engine.Debug;

public class RspHandler {
	private byte[] rsp = null;
	IMercuryListenter listener = new IMercuryListenter() {
		
		@Override
		public void onReceive(String s) {
			// TODO Auto-generated method stub
			
		}
	};
	
	public void setListener(IMercuryListenter listener){
		this.listener = listener;
	}
	
	public synchronized boolean handleResponse(byte[] rsp) {
		this.rsp = rsp;
		this.notify();		
		String s = new String(this.rsp);
		Debug.trace("respone : ");
		//System.out.println(s);
		listener.onReceive(s);		
		return false;
	}
	
	public synchronized void waitForResponse() {
		while(this.rsp == null) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		Debug.trace("wait for respone : ");
		System.out.println(new String(this.rsp));
	}
}