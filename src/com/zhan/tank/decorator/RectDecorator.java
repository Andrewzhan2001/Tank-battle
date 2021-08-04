package com.zhan.tank.decorator;

import java.awt.Color;
import java.awt.Graphics;

import com.zhan.tank.GameModel;
import com.zhan.tank.GameObject;

public class RectDecorator extends GODecorator {

	public RectDecorator(GameObject go) {
		super(go);
	}

	@Override
	public int paint(Graphics g) {
		this.x = go.x;
		this.y = go.y;
		int check = go.paint(g);
		if (check == 0) {
			Color c = g.getColor();
			g.setColor(Color.yellow);
			g.drawRect(go.x, go.y, go.getHeight(), go.getWidth());
			g.setColor(c);
			return check;
		} else {
			GameModel.getInstance().remove(this);
			return 1;
		}
			
		
	}

	@Override
	public int getWidth() {
		return go.getWidth();
	}

	@Override
	public int getHeight() {
		return go.getHeight();
	}
}