package org.gsn.engine;

public class Debug {
	public static void trace(Object obj) {
		System.out.println(obj);
	}

	public static void e(Exception e) {
		// TODO Auto-generated method stub
		e.printStackTrace();
	}
}
