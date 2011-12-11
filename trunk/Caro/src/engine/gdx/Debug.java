package engine.gdx;

import com.badlogic.gdx.Gdx;

public class Debug {
	public static void trace(Object obj){
		System.out.println(obj);
	}
	
	public static void trace(String tag, Object obj){
		if (tag.equals("OUT"))
			System.out.println(obj);
		else 
			Gdx.app.log(tag, obj.toString());
	}
}
