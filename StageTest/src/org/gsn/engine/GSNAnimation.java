package org.gsn.engine;

import java.util.List;

import org.gsn.engine.GsnTextureAtlas.AtlasRegion;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GSNAnimation {
	public float startTime;
	final TextureRegion[] keyFrames;
	public float frameDuration;
	public Boolean isFinish;

	public GSNAnimation(float frameDuration, List<AtlasRegion> a) {
		this.frameDuration = frameDuration;
		this.keyFrames = new TextureRegion[a.size()];
		for (int i = 0, n = a.size(); i < n; i++) {
			this.keyFrames[i] = a.get(i);
		}
	}

	public void resetTime(float time) {
		startTime = time;
		isFinish = false;
	}

	// public Boolean isFinish(float time){
	// int frameNumber = (int)((time-startTime) / frameDuration);
	// if (frameNumber >= keyFrames.length) return true;
	// else return false;
	// }

	public TextureRegion getStaticKeyFrame(int index) {
		return keyFrames[index];
	}

	public TextureRegion getKeyFrame(float stateTime, boolean looping) {
		int frameNumber = (int) ((stateTime - startTime) / frameDuration);
		if (!looping) {
			if (frameNumber >= keyFrames.length)
				isFinish = true;
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);

		} else {
			frameNumber = frameNumber % keyFrames.length;
		}
		return keyFrames[frameNumber];
	}
}
