package engine;

import com.badlogic.gdx.Gdx;

public class Debug {
	public static void trace(Object obj){
		System.out.println(obj);
	}
	
	public static void trace(String tag, Object obj){
		Gdx.app.log(tag, obj.toString());
	}
}
