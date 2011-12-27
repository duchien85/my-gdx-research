package org.gsn.caro.lobby;

import org.gsn.engine.TwoStateButton;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BetButton extends TwoStateButton{
	public final static int BET_GOLD=1;
	public final static int BET_COIN=2;
	
	public int betValue;
	public int betType;
	public float dx;
	public float dy;
	
	public BetButton(int betValue, int betType, TextureRegion enableTex, TextureRegion disableTex,TextureRegion downTex, float width, float height){
		super(enableTex,disableTex, downTex,width, height);
		this.betType = betType;
		this.betValue = betValue;
		this.dx = 0;
		this.dy = 0;
	}
	
	public BetButton(int betValue, int betType, TextureRegion enableTex, TextureRegion disableTex,TextureRegion downTex, float dx, float dy, float width, float height){
		super(enableTex,disableTex, downTex,width, height);
		this.betType = betType;
		this.betValue = betValue;
		this.dx = dx;
		this.dy = dy;
	}
		
}
