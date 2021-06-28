package com.zhan.tank;

public class MainF {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        
        int initTankCount = PropertyMgr.getInt("initTankCount");
        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tf.enemys.add(new Tank(50+i*80, 200, Dir.down,Group.Enemy,tf));
        }

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while (true) {
            Thread.sleep(50);
            tf.repaint();
            
        }
    }
}
