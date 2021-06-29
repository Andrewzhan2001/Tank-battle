package com.zhan.tank.abstractfactory;

import java.awt.Graphics;


public abstract class BaseBullet {
	public abstract int paint(Graphics g);

	public abstract void collideWith(BaseTank tank);
}
