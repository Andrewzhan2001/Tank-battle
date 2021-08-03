package com.zhan.tank.chainofresponsibility;

import com.zhan.tank.GameObject;
import com.zhan.tank.Tank;
import com.zhan.tank.Wall;
import com.zhan.tank.Group;
public class TankWallCollider implements Collider{
  @Override
  public boolean collide(GameObject o1, GameObject o2) {
    if (o1 instanceof Tank && o2 instanceof Wall) {
      Tank t = (Tank)o1;
      Wall w = (Wall)o2;
      
      if(t.getrect().intersects(w.rect) && t.getGroup() == Group.Enemy) {
				t.back();
			}
    } else if(o1 instanceof Wall && o2 instanceof Tank){
      return collide(o2,o1);
    }
    return true;
  }
}
