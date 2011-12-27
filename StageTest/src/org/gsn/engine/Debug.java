package org.gsn.engine;

import android.util.Log;

public class Debug {
	public static void trace(Object obj) {	
//		if (obj!=null)
//			Log.i("log", obj.toString());
		System.out.println(obj);
	}

	public static void e(Exception e) {
		// TODO Auto-generated method stub
		e.printStackTrace();
	}
}
