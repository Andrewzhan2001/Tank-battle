package com.zhan.tank;

import java.awt.Graphics;

import com.zhan.tank.abstractfactory.BaseExplode;

public class Explode extends BaseExplode{
    public static int width = ResourceCtrl.explodes[0].getWidth();
    public static int height = ResourceCtrl.explodes[0].getHeight();
    private int x, y;
    private int step = 0;
    TankFrame tf = null;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(()->new Audio("audio/explode.wav").play()).start();//new的时候播放音乐
    }
    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceCtrl.explodes[step++], x, y, null);
        if(step >= ResourceCtrl.explodes.length) {
            tf.explodes.remove(this);
        };
    }
}
