package com.zhan.tank.chainofresponsibility;

import com.zhan.tank.Bullet;
import com.zhan.tank.Explode;
import com.zhan.tank.GameObject;
import com.zhan.tank.Group;
import com.zhan.tank.Tank;

public class BulletTankCollider implements Collider {

  @Override
	public boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Bullet && o2 instanceof Tank) {
			Bullet b = (Bullet)o1;
			Tank t = (Tank)o2;
			if(b.getGroup() == t.getGroup()) return true;
			if(b.rect.intersects(t.getrect()) && t.getGroup() == Group.Enemy) {
				t.die();
				b.die();
				int eX = t.getX() + t.getImage().getWidth()/2 - Explode.width/2;
				int eY = t.getY() + t.getImage().getHeight()/2 - Explode.height/2;
				new Explode(eX, eY);
				return false;
			}
			
		} else if (o1 instanceof Tank && o2 instanceof Bullet) {
			return collide(o2, o1);
		} 
		
		return true;
		
	}
  
}
