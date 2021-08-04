package com.zhan.tank.firestrategy;

import com.zhan.tank.Audio;
import com.zhan.tank.Bullet;
import com.zhan.tank.Dir;
import com.zhan.tank.GameModel;
import com.zhan.tank.Group;
import com.zhan.tank.Tank;
import com.zhan.tank.decorator.RectDecorator;
import com.zhan.tank.decorator.TailDecorator;

public class FourDirFireStrategy implements FireStrategy{
  @Override
  public void fire(Tank t) {
    int bulletx = t.getX() + t.getImage().getWidth()/2 - t.bimage.getWidth()/2;
    int bullety = t.getY() + t.getImage().getHeight()/2 - t.bimage.getHeight()/2;

    Dir[] dirs = Dir.values();
    for (Dir dir : dirs) {
      GameModel.getInstance().add(new TailDecorator(new RectDecorator(new Bullet(bulletx, bullety, dir, t.getGroup()))));
    }
    if (t.getGroup() == Group.my) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
  }
}
