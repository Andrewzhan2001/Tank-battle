package com.zhan.tank;

import java.awt.Graphics;

public class Explode extends GameObject{
    public static int width = ResourceCtrl.explodes[0].getWidth();
    public static int height = ResourceCtrl.explodes[0].getHeight();
    private int x, y;
    private int step = 0;
    GameModel gm = null;

    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
        new Thread(()->new Audio("audio/explode.wav").play()).start();//new的时候播放音乐
    }
    public int paint(Graphics g) {
        g.drawImage(ResourceCtrl.explodes[step++], x, y, null);
        if(step >= ResourceCtrl.explodes.length) {
            gm.remove(this);
            return 1;
        };
        return 0;
    }
}
