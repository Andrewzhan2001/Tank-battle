package com.zhan.tank.decorator;

import java.awt.Color;
import java.awt.Graphics;

import com.zhan.tank.GameModel;
import com.zhan.tank.GameObject;

public class TailDecorator extends GODecorator {

	public TailDecorator(GameObject go) {
		
		super(go);
	}

	@Override
	public int paint(Graphics g) {
		this.x = go.x;
		this.y = go.y;
		int check = go.paint(g);
		if (check == 0) {
			Color c = g.getColor();
			g.setColor(Color.WHITE);
			g.drawLine(go.x, go.y, go.x + getWidth(), go.y + getHeight());
			g.setColor(c);
			return 0;
		} else {
			GameModel.getInstance().remove(this);
			return 1;
		}
		
	}
	
	
	@Override
	public int getWidth() {
		return super.go.getWidth();
	}

	@Override
	public int getHeight() {
		return super.go.getHeight();
	}

}
