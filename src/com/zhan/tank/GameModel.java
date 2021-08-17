package com.zhan.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.zhan.tank.chainofresponsibility.ColliderChain;

// serializable 对象序列化，将对象的字节一模一样写到文件里面
// 只有当所有class里面的属性全部都实现了serializable接口才能写入文件
public class GameModel implements Serializable{//这里储存了所有的实体

  private static final GameModel INSTANCE = new GameModel();
	static{
    INSTANCE.init();
  }
  Tank mytank;
 /*  List<Bullet> bullets = new ArrayList<>();//容器里面不清掉就有内存泄漏;
  List<Tank> enemys = new ArrayList<>();
  List<Explode> explodes = new ArrayList<>(); */
  ColliderChain chain = new ColliderChain();
  private List<GameObject> objects = new ArrayList<>();

  public static GameModel getInstance() {
		return INSTANCE;
	}

  public void init() {
    mytank = new Tank(200, 200, Dir.up, Group.my);
    int initTankCount = PropertyMgr.getInt("initTankCount");
    //初始化敌方坦克
    for (int i = 0; i < initTankCount; i++) {
      new Tank(50+i*80, 200, Dir.down,Group.Enemy);
    }
    
    //初始化墙
    add(new Wall(150, 150, 200, 50));
		add(new Wall(550, 150, 200, 50));
		add(new Wall(300, 300, 50, 200));
		add(new Wall(550, 300, 50, 200));
  }

  public void add(GameObject go) {
    objects.add(go);
  }
  public void paint(Graphics g) {
    System.out.println(objects.size());
/*  Color c = g.getColor();
     g.setColor(Color.RED);
    g.drawString("Bullet number:"+bullets.size(), 10, 60);
    g.drawString("Enemy number:"+enemys.size(), 10, 80);
    g.drawString("explode number:"+explodes.size(), 10, 100);
    g.setColor(c);
    for (int i = 0; i < bullets.size(); i++) {//直接遍历的时候不允许增改减（fail-fast)，只针对原集合（用concurrent里面他会拷贝进行遍历,在最后再放回去）
        int check = bullets.get(i).paint(g);
        if (check == 1) i--;
    } */
    /* for (Iterator<Bullet> t = bullets.iterator(); t.hasNext();) {
        Bullet b = t.next();第一次调用会返回iterator的第一个元素
        if(!b.live) it.remove();
    } */

    for (int i = 0; i < objects.size(); i++) {//tank paint
        int check = objects.get(i).paint(g);
        if (check == 1) i--;
    }

    /* for (int i = 0; i < explodes.size(); i++) {//explode paint
        explodes.get(i).paint(g);
    }

    for (int i = 0; i < bullets.size(); i++) {//collision
        for (int j = 0; j < enemys.size(); j++) {
            bullets.get(i).collideWith(enemys.get(j));
        }
    } */
    for (int i = 0; i < objects.size(); i++) {
      for (int j = i+1; j < objects.size(); j++) {
        GameObject o1 = objects.get(i);
        GameObject o2 = objects.get(j);
        chain.collide(o1, o2);
      }
    }
    mytank.paint(g);
  }

	public Tank getMainTank() {
		return mytank;
	}
  
  public void remove(GameObject go) {
    objects.remove(go);
  }

  public void save() {
		File f = new File("c:/zhan/tank.data");
		ObjectOutputStream oos = null;
		try {
      // 导入一个outputstream参数，objectOutputstream将东西写入后面的outputstream
			oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(mytank);
			oos.writeObject(objects);//list全都实现了serializable接口
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

      // finally 块中定义的代码，总是在 try 和任何 catch 块之后、方法完成之前运行
      // 正常情况下，不管是否抛出或捕获异常 finally 块都会执行
		} finally {
			if(oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void load() {
		File f = new File("c:/zhan/tank.data");
    ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(f));
			mytank = (Tank)ois.readObject();
			objects = (List)ois.readObject();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
