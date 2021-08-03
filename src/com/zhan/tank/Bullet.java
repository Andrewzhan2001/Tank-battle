package com.zhan.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject{
    private static final int speed = PropertyMgr.getInt("bspeed");
    private int x,y;
    private Dir dir;
    private boolean alive = true;
    private GameModel gm = null;
    private Group group = Group.Enemy;
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
    private BufferedImage image= ResourceCtrl.bulletU;
    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public Bullet(int x, int y, Dir dir, Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gm = gm;
        gm.add(this);
    }
    private Rectangle rect = new Rectangle(this.x, this.y, image.getWidth(),image.getHeight());
    private void move() {
        switch (dir) {
            case left:
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
        if (x < 0 || y < 0 || x > TankFrame.sc_width || y > TankFrame.sc_height) {
            die();
        }
        //update rect
        rect.x = this.x;
        rect.y = this.y;
        rect.width = image.getWidth();
        rect.height = image.getHeight();
    }
    public boolean collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return false;
        if (rect.intersects(tank.getrect())) {
            tank.die();
            this.die();
            int ex = tank.getX() + tank.getImage().getWidth()/2 - Explode.width/2;
            int ey = tank.getY() + tank.getImage().getHeight()/2 - Explode.height/2;
            gm.add(new Explode(ex,ey,gm));
            return true;
        }
        return false;
    }
    private void die() {
        this.alive = false;
    }
    public int paint(Graphics g) {
        if (!alive) {
            gm.remove(this);
            return 1;//not alive return 1
        }
        switch (dir) {
            case down:
                image = ResourceCtrl.bulletD;
                break;
            case left:
                image = ResourceCtrl.bulletL;
                break;
            case right:
                image = ResourceCtrl.bulletR;
                break;
            case up:
                image = ResourceCtrl.bulletU;
                break;       
        }
        g.drawImage(image, x, y, null);
        move();
        return 0;//succeed return 0
    }
}
