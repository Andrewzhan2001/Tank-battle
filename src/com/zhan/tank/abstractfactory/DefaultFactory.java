package com.zhan.tank.abstractfactory;

import com.zhan.tank.Bullet;
import com.zhan.tank.Dir;
import com.zhan.tank.Explode;
import com.zhan.tank.Group;
import com.zhan.tank.Tank;
import com.zhan.tank.TankFrame;

public class DefaultFactory extends GameFactory {

	@Override
	public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new Tank(x, y, dir, group, tf);
	}

	@Override
	public BaseExplode createExplode(int x, int y, TankFrame tf) {
		return new Explode(x, y, tf);
	}

	@Override
	public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new Bullet(x, y, dir, group, tf);
	}

}
