package com.zhan.tank.chainofresponsibility;

import com.zhan.tank.GameObject;
import com.zhan.tank.Tank;
import com.zhan.tank.Group;

public class TankTankCollider implements Collider{
  @Override
  public boolean collide(GameObject o1, GameObject o2) {
    if(o1 instanceof Tank && o2 instanceof Tank){
      Tank t1 = (Tank)o1;
      Tank t2 = (Tank)o2;
      if(t1.getrect().intersects(t2.getrect()) && t1.getGroup() == Group.Enemy) {
        t1.back();
        t2.back();
        return false;
      }
    } 
    return true;
  }
}
