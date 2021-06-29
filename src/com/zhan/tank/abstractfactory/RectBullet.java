package com.zhan.tank.abstractfactory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.zhan.tank.Dir;
import com.zhan.tank.Explode;
import com.zhan.tank.Group;
import com.zhan.tank.ResourceCtrl;
import com.zhan.tank.TankFrame;

public class RectBullet extends BaseBullet {
	private static final int SPEED = 6;
	public static int WIDTH = ResourceCtrl.bulletD.getWidth();
	public static int HEIGHT = ResourceCtrl.bulletD.getHeight();
	
	Rectangle rect = new Rectangle();
	
	private int x, y;
	private Dir dir;
	
	private boolean living = true;
	TankFrame tf = null;
	private Group group = Group.Enemy;
	
	public RectBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
		
		rect.x = this.x;
		rect.y = this.y;
		rect.width = WIDTH;
		rect.height = HEIGHT;
		
		tf.bullets.add(this);
				
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int paint(Graphics g) {
		if(!living) {
			tf.bullets.remove(this);
			return 1;
		}
		
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 20, 20);
		g.setColor(c);
		
		move();
		return 0;
	}
	
	private void move() {
		
		switch (dir) {
		case left:
			x -= SPEED;
			break;
		case up:
			y -= SPEED;
			break;
		case right:
			x += SPEED;
			break;
		case down:
			y += SPEED;
			break;
		}
		
		//update rect
		rect.x = this.x;
		rect.y = this.y;
		
		if(x < 0 || y < 0 || x > TankFrame.sc_width || y > TankFrame.sc_height) living = false;
		
	}

	public void collideWith(BaseTank tank) {
		if(this.group == tank.getGroup()) return;
		
		if(rect.intersects(tank.rect)) {
			tank.die();
			this.die();
			int eX = tank.getX() + tank.getImage().getWidth()/2 - Explode.width;
			int eY = tank.getY() + tank.getImage().getHeight()/2 - Explode.height/2;
			tf.explodes.add(tf.gf.createExplode(eX, eY, tf));
		}
		
	}

	private void die() {
		this.living = false;
	}
}
