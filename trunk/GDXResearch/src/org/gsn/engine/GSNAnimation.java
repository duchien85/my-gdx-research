package org.gsn.engine;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GSNAnimation extends Animation {

	public GSNAnimation(float frameDuration, List<TextureRegion> keyFrames) {
		super(frameDuration, keyFrames);
	}

	public GSNAnimation(float frameDuration, TextureRegion... keyFrames) {
		super(frameDuration, keyFrames);
		// TODO Auto-generated constructor stub
	}
}
