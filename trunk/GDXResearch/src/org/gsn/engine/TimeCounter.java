package org.gsn.engine;

public class TimeCounter {
	private long time = 0;
	public long count(){
		long tmp = System.currentTimeMillis();
		long period = tmp - time;
		time = tmp;
		return period;
	}
}
