package com.gsn;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actors.Image;

public class MyStage extends Stage{
	static final String tag = "MyApp";	
	
	Vector2 point = new Vector2();
	Group group;
	Image monkey;
	Image avatar;
	BitmapFont font;
	public MyStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		// TODO Auto-generated constructor stub
		init();		
	}	
	
	public void init(){
		font = new BitmapFont();
		group = new Group("group avatar");
		avatar = new Image("avatar", TextureAsset.avatar);
		avatar.x = 0;
		avatar.y = 0;
		
		monkey = new Image("monkey", TextureAsset.monkey);
		monkey.x = 30;
		monkey.y = 0;
		
		group.x = 100;
		group.y = 100;
		group.width = 100;
		group.height = 100;
		
		group.addActor(avatar);
		group.addActor(monkey);			
		this.addActor(group);
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		toStageCoordinates(x, y, point);
		Gdx.app.log(tag, "hit : " + x + " " + y + " ---> " + point.x + " : " + point.y);
		Actor actor = hit(point.x, point.y);
		if (actor == null)
			Gdx.app.log(tag, "nothing to hit");
		else {
			if (actor == avatar){
				Gdx.app.log(tag, "touch : avatar ");	
				group.x = width() - group.x;
				//avatar.y = this.height() - avatar.y;
			} else if (actor == monkey){
				Gdx.app.log(tag, "touch : monkey ");	
				avatar.x = group.width - avatar.x;
				//group.y = this.height() - group.y;
			}
				
		}
		return false;
	}

}
