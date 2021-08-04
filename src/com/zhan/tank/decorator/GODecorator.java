package com.zhan.tank.decorator;

import java.awt.Graphics;

import com.zhan.tank.GameObject;

public abstract class GODecorator extends GameObject {
	
	GameObject go;
	
	public GODecorator(GameObject go) {
		
		this.go = go;
	}

	@Override
	public int paint(Graphics g){
		return go.paint(g);
	}

}
