package org.gsn.engine;

import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;

public class MenuSpirte extends CustomSprite {
	public static interface IMenuListener{
		void clickMenuItem(int index);
	}
	
	private BitmapFont font;
	private List<String> itemList;
	private IMenuListener listener;

	public MenuSpirte(BitmapFont font, float width, String... items) {
		this.font = font;
		List<String> itemList = new ArrayList<String>();
		for (int i = 0; i < items.length; i++) {
			itemList.add(items[i]);
		}
		init(font, itemList, width);
	}
	
	public void setListener(IMenuListener listener){
		this.listener = listener;
		
	}

	private float width;

	private void init(BitmapFont font, List<String> items, float width) {
		this.font = font;
		itemList = new ArrayList<String>(items);
		this.width = width;
		recList = new ArrayList<Rectangle>();
		rectBound = new Rectangle(0, 0, width, items.size() * font.getLineHeight());
	}

	public MenuSpirte(BitmapFont font, float width, List<String> items) {
		init(font, items, width);
	}
		

	private List<Rectangle> recList;

	@Override
	public void draw(SpriteBatch batcher) {
		// TODO Auto-generated method stub
		int count = 0;
		for (int i = itemList.size() - 1; i >= 0; i--) {
			String text = itemList.get(i);
			//Debug.trace("text : " + text);
			float tx, ty;
			tx = x;
			ty = y + font.getLineHeight() * (count + 1);
			TextBounds bounds = font.drawWrapped(batcher, text, tx, ty, width, HAlignment.CENTER);
			// Debug.trace("bound : " + tx + " " + ty + " " + bounds.width + " "
			// + bounds.height);
			count++;
		}

	}


	@Override
	public void touchDown(float localX, float localY) {
		// TODO Auto-generated method stub
		int index =  itemList.size() - 1 - (int) (localY / font.getLineHeight());		
		if (listener != null)
			listener.clickMenuItem(index);
	}			
}
