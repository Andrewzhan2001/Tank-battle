package com.zhan.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;


public class TankFrame extends Frame {
    static final int sc_width = PropertyMgr.getInt("scwidth"), sc_height = PropertyMgr.getInt("scheight");
    Tank mytank = new Tank(200, 200, Dir.up, Group.my, this);
    List<Bullet> bullets = new ArrayList<>();//容器里面不清掉就有内存泄漏
    List<Tank> enemys = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();
    public TankFrame() {
        setSize(sc_width, sc_height);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {// 添加监视器，窗口关闭时，监听器会调用对象中相关方法
            @Override // adapter为空方法（抽象类），只用来重写你关注的方法（作为superclass）
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    Image offScreenImage = null;
    @Override//原先repaint是先调用update在update里会调用paint
    public void update(Graphics g) {//双缓冲,把所有东西先存在内存里在一次性画出来
        if (offScreenImage == null) {
            offScreenImage = createImage(sc_width, sc_height);
        }
        Graphics goffScreen = offScreenImage.getGraphics();
        Color c = goffScreen.getColor();
        goffScreen.setColor(Color.BLACK);
        goffScreen.fillRect(0, 0, sc_width, sc_height);
        goffScreen.setColor(c);
        paint(goffScreen);//往图片里涂
        g.drawImage(offScreenImage, 0, 0, null);//显示回屏幕
    }

    @Override
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

    class MyKeyListener extends KeyAdapter {
        boolean left = false;
        boolean right = false;
        boolean up = false;
        boolean down = false;
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_A:
                    left = true;
                    break;
                case KeyEvent.VK_D:
                    right = true;
                    break;
                case KeyEvent.VK_W:
                    up = true;
                    break;
                case KeyEvent.VK_S:
                    down = true;
                    break;
                case KeyEvent.VK_J:
                    mytank.fire();
                    break;
            }
            setTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_A:
                    left = false;
                    break;
                case KeyEvent.VK_D:
                    right = false;
                    break;
                case KeyEvent.VK_W:
                    up = false;
                    break;
                case KeyEvent.VK_S:
                    down = false;
                    break;
            }
            setTankDir();
        }
        private void setTankDir() {
            if(!left && !right && !up && !down) mytank.setMoving(false); 
            else {
                mytank.setMoving(true);
                if (left) mytank.setDir(Dir.left);
                if (right) mytank.setDir(Dir.right);
                if (up) mytank.setDir(Dir.up);
                if (down) mytank.setDir(Dir.down);
            } 
        }
    }
}
