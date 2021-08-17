package com.zhan.tank.firestrategy;

import java.io.Serializable;

import com.zhan.tank.Tank;

public interface FireStrategy extends Serializable {
  void fire(Tank t);
}
