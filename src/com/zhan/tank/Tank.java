package com.zhan.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Tank {
    private int x, y;
    public int getX() {
        return x;
    }
    public Rectangle getrect() {
        return rect;
    }
    public void setrect(Rectangle rect) {
        this.rect = rect;
    }
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    private Dir dir = Dir.up;
    private boolean moving = true;
    private boolean alive = true;
    private static final int speed = PropertyMgr.getInt("tspeed");
    private Random random = new Random();
    private Group group = Group.Enemy;
    private BufferedImage image= ResourceCtrl.tankU;
    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    private BufferedImage bimage = ResourceCtrl.bulletU;
    private TankFrame tf;
    public Dir getDir() {
        return dir;
    }
    public void setDir(Dir dir) {
        this.dir = dir;
    }
    public boolean isMoving() {
        return moving;
    }
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    private Rectangle rect = new Rectangle(x,y,image.getWidth(), image.getHeight());
    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.setGroup(group);
        this.tf = tf;
    }
    private void move() {
        if (!moving) return;
        switch (dir) {
            case left://switch 直接能enum里面的参数
                x-=speed;
                break;
            case right:
                x+=speed;
                break;
            case up:
                y-=speed;
                break;
            case down:
                y+=speed;
                break;
        }
        if (random.nextInt(10) > 8 && this.group == Group.Enemy) {
            this.fire();
            ramdomDir();
        }
        boudscheck();

        //update rect
        rect.x = this.x;
        rect.y = this.y;
        rect.width = image.getWidth();
        rect.height = image.getHeight();
    }
    private void boudscheck() {
        if (this.x < 5) x = 5;
        if (this.y < 30) y = 30;
        if (this.x > TankFrame.sc_width - image.getWidth() -5) x = TankFrame.sc_width - image.getWidth() -5;
        if (this.y > TankFrame.sc_height - image.getHeight() -5) y = TankFrame.sc_height - image.getHeight() -5;
    }
    private void ramdomDir() {
        this.dir = Dir.values()[random.nextInt(4)];//dir.values返回数组[L,R,U,D]
    }
    public int paint(Graphics g) {
        if (!alive) {
            tf.enemys.remove(this);
            return 1;//not alive return 1
        }
        switch (dir) {
            case down:
                image = (this.group == Group.my? ResourceCtrl.tankD : ResourceCtrl.BtankD);
                bimage = ResourceCtrl.bulletD;
                break;
            case left:
                image = (this.group == Group.my? ResourceCtrl.tankL : ResourceCtrl.BtankL);
                bimage = ResourceCtrl.bulletL;
                break;
            case right:
                image = (this.group == Group.my? ResourceCtrl.tankR : ResourceCtrl.BtankR);
                bimage = ResourceCtrl.bulletR;
                break;
            case up:
                image = (this.group == Group.my? ResourceCtrl.tankU : ResourceCtrl.BtankU);
                bimage = ResourceCtrl.bulletU;
                break;       
        }
        g.drawImage(image, x, y, null);
        move();
        return 0;
    }
    public void fire() {
        int bulletx = this.x + this.image.getWidth()/2 - bimage.getWidth()/2;
        int bullety = this.y + this.image.getHeight()/2 - bimage.getHeight()/2;
        tf.bullets.add(new Bullet(bulletx, bullety, this.dir, this.group, this.tf));
        if (this.group == Group.my) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
    public void die() {
        this.alive = false;
    }
}
