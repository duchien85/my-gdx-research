package org.gsn.caro;

import java.util.ArrayList;
import java.util.List;

import org.gsn.engine.CombineSprite;
import org.gsn.engine.Debug;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.math.Rectangle;

public class MenuSpirte extends CombineSprite {
	private BitmapFont font;
	private List<String> itemList;

	public MenuSpirte(BitmapFont font, float width, String... items) {
		this.font = font;
		List<String> itemList = new ArrayList<String>();
		for (int i = 0; i < items.length; i++) {
			itemList.add(items[i]);
		}
		init(font, itemList, width);
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
		Debug.trace(index);
	}			
}
