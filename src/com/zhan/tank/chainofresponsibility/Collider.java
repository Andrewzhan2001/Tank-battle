package com.zhan.tank.chainofresponsibility;

import com.zhan.tank.GameObject;

public interface Collider {
  boolean collide(GameObject o1, GameObject o2);
}
