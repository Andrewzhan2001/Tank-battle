package com.zhan.tank.abstractfactory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.zhan.tank.Audio;
import com.zhan.tank.DefaultFireStrategy;
import com.zhan.tank.Dir;
import com.zhan.tank.FireStrategy;
import com.zhan.tank.Group;
import com.zhan.tank.PropertyMgr;
import com.zhan.tank.ResourceCtrl;
import com.zhan.tank.TankFrame;

public class RectTank extends BaseTank {

	private static final int SPEED = 2;
	public static int WIDTH = 40;

	public static int HEIGHT = 40;


	private Random random = new Random();

	int x, y;

	Dir dir = Dir.down;

	private boolean moving = true;
	TankFrame tf = null;
	private boolean living = true;
	Group group = Group.Enemy;

	FireStrategy fs;

	public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;

		if (group == Group.my) {
			String goodFSName = (String) PropertyMgr.get("goodFS");

			try {
				fs = (FireStrategy) Class.forName(goodFSName).getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			fs = new DefaultFireStrategy();
		}
	}
	public Rectangle rect = new Rectangle(x,y,WIDTH,HEIGHT);

	public void fire() {
		// fs.fire(this);

		int bX = this.x + RectTank.WIDTH / 2 - ResourceCtrl.bulletU.getWidth() / 2;
		int bY = this.y + RectTank.HEIGHT / 2 - ResourceCtrl.bulletU.getHeight() / 2;

		Dir[] dirs = Dir.values();
		for (Dir dir : dirs) {
			tf.gf.createBullet(bX, bY, dir, group, tf);
		}

		if (group == Group.my)
			new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
	}

	public Dir getDir() {
		return dir;
	}

	public int getX() {
		return x;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int getY() {
		return y;
	}

	public boolean isMoving() {
		return moving;
	}

	private void move() {

		if (!moving)
			return;

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

		if (this.group == Group.Enemy && random.nextInt(100) > 95)
			this.fire();

		if (this.group == Group.Enemy && random.nextInt(100) > 95)
			randomDir();

		boundsCheck();
		// update rect
		rect.x = this.x;
		rect.y = this.y;

	}

	private void boundsCheck() {
		if (this.x < 2)
			x = 2;
		if (this.y < 28)
			y = 28;
		if (this.x > TankFrame.sc_width - RectTank.WIDTH - 2)
			x = TankFrame.sc_width - RectTank.WIDTH - 2;
		if (this.y > TankFrame.sc_height - RectTank.HEIGHT - 2)
			y = TankFrame.sc_height - RectTank.HEIGHT - 2;
	}

	private void randomDir() {

		this.dir = Dir.values()[random.nextInt(4)];
	}

	public int paint(Graphics g) {
		if (!living){
			tf.enemys.remove(this);
			return 1;
		}
		Color c = g.getColor();
		g.setColor(group == Group.my ? Color.RED : Color.BLUE);
		g.fillRect(x, y, 40, 40);
		g.setColor(c);
		move();
		return 0;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void die() {
		this.living = false;
	}

	@Override
	public Rectangle getrect() {
		return rect;
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}

}
