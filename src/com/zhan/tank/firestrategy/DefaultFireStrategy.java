package com.zhan.tank.firestrategy;

import com.zhan.tank.Audio;
import com.zhan.tank.Bullet;
import com.zhan.tank.Group;
import com.zhan.tank.Tank;

public class DefaultFireStrategy implements FireStrategy{
  @Override
  public void fire(Tank t) {
    int bulletx = t.getX() + t.getImage().getWidth()/2 - t.bimage.getWidth()/2;
    int bullety = t.getY() + t.getImage().getHeight()/2 - t.bimage.getHeight()/2;
    new Bullet(bulletx, bullety, t.getDir(), t.getGroup(), t.gm);
    if (t.getGroup() == Group.my) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
  }
}
