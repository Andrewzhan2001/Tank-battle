package com.zhan.tank.observer;

import com.zhan.tank.Tank;

public class TankFireEvent {
	Tank tank;
	
	// constructor
	public TankFireEvent(Tank tank) {
		this.tank = tank;
	}

	public Tank getSource() {
		return tank;
	}
	

}
