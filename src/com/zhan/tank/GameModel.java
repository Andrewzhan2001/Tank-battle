package com.zhan.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
  Tank mytank = new Tank(200, 200, Dir.up, Group.my, this);
  List<Bullet> bullets = new ArrayList<>();//容器里面不清掉就有内存泄漏
  List<Tank> enemys = new ArrayList<>();
  List<Explode> explodes = new ArrayList<>();
  public GameModel() {
    int initTankCount = PropertyMgr.getInt("initTankCount");
    //初始化敌方坦克
    for (int i = 0; i < initTankCount; i++) {
      enemys.add(new Tank(50+i*80, 200, Dir.down,Group.Enemy,this));
    }
  }

  public void paint(Graphics g) {
    Color c = g.getColor();
    g.setColor(Color.RED);
    g.drawString("Bullet number:"+bullets.size(), 10, 60);
    g.drawString("Enemy number:"+enemys.size(), 10, 80);
    g.drawString("explode number:"+explodes.size(), 10, 100);
    g.setColor(c);
    for (int i = 0; i < bullets.size(); i++) {//直接遍历的时候不允许增改减（fail-fast)，只针对原集合（用concurrent里面他会拷贝进行遍历,在最后再放回去）
        int check = bullets.get(i).paint(g);
        if (check == 1) i--;
    }
    /* for (Iterator<Bullet> t = bullets.iterator(); t.hasNext();) {
        Bullet b = t.next();第一次调用会返回iterator的第一个元素
        if(!b.live) it.remove();
    } */

    for (int i = 0; i < enemys.size(); i++) {//tank paint
        int check = enemys.get(i).paint(g);
        if (check == 1) i--;
    }

    for (int i = 0; i < explodes.size(); i++) {//explode paint
        explodes.get(i).paint(g);
    }

    for (int i = 0; i < bullets.size(); i++) {//collision
        for (int j = 0; j < enemys.size(); j++) {
            bullets.get(i).collideWith(enemys.get(j));
        }
    }

    mytank.paint(g);
  }

	public Tank getMainTank() {
		return mytank;
	}
  
}
