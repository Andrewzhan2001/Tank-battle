package com.zhan.tank.abstractfactory;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.zhan.tank.Group;

public abstract class BaseTank {
	public Group group = Group.Enemy;
	public Rectangle rect = new Rectangle();
	
	public abstract int paint(Graphics g);

	public Group getGroup() {
		return this.group;
	}
	public abstract Rectangle getrect();
	public abstract BufferedImage getImage();

	public abstract void die();

	public abstract int getX();

	public abstract int getY();
}
