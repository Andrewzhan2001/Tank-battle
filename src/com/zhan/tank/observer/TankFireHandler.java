package com.zhan.tank.observer;

import com.zhan.tank.Tank;

public class TankFireHandler implements TankFireObserver {

	@Override
	public void actionOnFire(TankFireEvent e) {
		Tank t = e.getSource();
		t.fire();
	}

}
