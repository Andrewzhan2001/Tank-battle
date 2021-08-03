package com.zhan.tank.chainofresponsibility;

import java.util.LinkedList;
import java.util.List;

import com.zhan.tank.GameObject;



public class ColliderChain implements Collider{//两个chain也可以通过add来合并
  private List<Collider> colliders = new LinkedList<>();
  
  public ColliderChain() {
    add(new BulletTankCollider());
    add(new TankTankCollider());
  }

  public void add(Collider c) {
    colliders.add(c);
  }

  public boolean collide(GameObject o1, GameObject o2) {
    for (int i = 0; i < colliders.size(); i++) {
      if(!colliders.get(i).collide(o1, o2)){
        return false;
      }
    }
    return true;
  }
}
