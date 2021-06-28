package com.zhan.tank;

public class FourDirFireStrategy implements FireStrategy{
  @Override
  public void fire(Tank t) {
    int bulletx = t.getX() + t.getImage().getWidth()/2 - t.bimage.getWidth()/2;
    int bullety = t.getY() + t.getImage().getHeight()/2 - t.bimage.getHeight()/2;

    Dir[] dirs = Dir.values();
    for (Dir dir : dirs) {
      new Bullet(bulletx, bullety, dir, t.getGroup(), t.getTf());
    }
    if (t.getGroup() == Group.my) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
  }
}
