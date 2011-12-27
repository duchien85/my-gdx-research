package org.gsn.caro.lobby;

import org.gsn.engine.CustomSprite;
import org.gsn.engine.Debug;
import org.gsn.engine.GSNScreen;
import org.gsn.game.CaroAssets;
import org.gsn.game.Constant;
import org.gsn.game.DataProvider;

import android.widget.Toast;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GuiBet extends Group{

//	private BetButton bet100;
//	private BetButton bet500;
//	private BetButton bet5000;
//	private BetButton bet1G;
//	private BetButton bet10G;
	
	private final static int NUM_BET = 5;
	private BetButton[] bets;
	private Image chon; 
	
	public int selectedIndex = -1;
	public int betType;
	public int betValue;
	public float dx = 10;
	public float dy = -20;
	private LobbyScreen screen;
	
	public GuiBet(){		
		//setContent();
	}
	
	public void setLobby(LobbyScreen lc){
		screen = lc;
	}
	
	public void setContent(){
		bets = new BetButton[5];
		bets[0] = new BetButton(100, BetButton.BET_GOLD, CaroAssets.getInstance().bet100_1, CaroAssets.getInstance().bet100_2,CaroAssets.getInstance().bet100_3,55,77);
		bets[1] = new BetButton(500, BetButton.BET_GOLD, CaroAssets.getInstance().bet500_1, CaroAssets.getInstance().bet500_2,CaroAssets.getInstance().bet500_3,-2,5,65,64);
		bets[2] = new BetButton(5000, BetButton.BET_GOLD, CaroAssets.getInstance().bet5000_1, CaroAssets.getInstance().bet5000_2,CaroAssets.getInstance().bet5000_3,-2,10,55,65);
		bets[3] = new BetButton(1, BetButton.BET_COIN, CaroAssets.getInstance().bet1G_1, CaroAssets.getInstance().bet1G_2,CaroAssets.getInstance().bet1G_3,64,74);
		bets[4] = new BetButton(10, BetButton.BET_COIN, CaroAssets.getInstance().bet10G_1, CaroAssets.getInstance().bet10G_2,CaroAssets.getInstance().bet10G_3,65,70);
		chon = screen.createImage(CaroAssets.getInstance().chon,60,62);
		
		float centerX = Constant.WIDTH/2;
		float centerY = Constant.HEIGHT/2;
		
		bets[0].setPosition(dx + 60 - bets[0].getWidth()/2, dy + 170 - bets[0].getHeight()/2);
		bets[1].setPosition(dx + 160 - bets[1].getWidth()/2, dy + 190 - bets[1].getHeight()/2);
		bets[2].setPosition(dx + 255 - bets[2].getWidth()/2, dy + 170 - bets[2].getHeight()/2);
		bets[3].setPosition(dx + 107 - bets[3].getWidth()/2, dy + 55 - bets[3].getHeight()/2);
		bets[4].setPosition(dx + 207 - bets[4].getWidth()/2, dy + 55 - bets[4].getHeight()/2);
		
		this.addActor(chon);
		for (int i=0; i<NUM_BET; i++)
		{			
			this.addActor(bets[i]);
			bets[i].setEnable(true);
		}
		chon.visible = false;
//		bets[0].setPosition(centerX/2 - bets[0].getWidth()/2, centerY*3/2 - bets[0].getHeight()/2);
//		bets[1].setPosition(centerX - bets[1].getWidth()/2, centerY*3.5f/2-bets[1].getHeight()/2);
//		bets[2].setPosition(centerX*3/2 - bets[2].getWidth()/2, centerY*3/2-bets[2].getHeight()/2);
//		bets[3].setPosition(centerX*3/4 - bets[3].getWidth()/2, centerY/2-bets[3].getHeight()/2);
//		bets[4].setPosition(centerX*5/4 - bets[4].getWidth()/2, centerY/2 -bets[4].getHeight()/2);
	}
	
	public void initBet(){
		if (selectedIndex<0)
		if (DataProvider.getInstance().getGold()>=bets[0].betValue){		
			selectedIndex = 0;
			betType = bets[0].betType;				
			betValue = bets[0].betValue;			
		}
		updateIndex();
	}
	
	public void touchUp(float localX, float localY){	
		for (int i=0; i<NUM_BET; i++){
			if (screen.isTouchIn(bets[i],localX, localY)){
				if (bets[i].isEnable())	{
					//bets[i].setIsPressed(true);
					selectedIndex = i;
					betType = bets[i].betType;				
					betValue = bets[i].betValue;
					//screen.showEffect(bets[i].getMiddleX()+bets[i].dx,bets[i].getMiddleY()+bets[i].dy);
					break;
				}else
				{
					screen.notEnoughMoney();
				}
			}
		}		
		
	}
	
	public void updateAccount(){
		int gold = DataProvider.getInstance().getGold();
		int g = DataProvider.getInstance().getXu();
		for (int i=0; i<NUM_BET; i++){
			if (bets[i].betType == Constant.BET_GOLD) bets[i].setEnable(bets[i].betValue<=gold);
			if (bets[i].betType == Constant.BET_COIN) bets[i].setEnable(bets[i].betValue<=g);
		}
		if (selectedIndex>=0){
			if (!bets[selectedIndex].isEnable()){
				selectedIndex = -1;
				initBet();
			}
		}
		updateIndex();
	}
	public void updateIndex(){
		if (selectedIndex>0){
			chon.visible = true;
			chon.x = bets[selectedIndex].x;
			chon.y = bets[selectedIndex].y;
		}else chon.visible = false;
	}
}
