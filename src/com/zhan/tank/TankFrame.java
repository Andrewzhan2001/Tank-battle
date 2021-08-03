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

//MVC view输入通过control来控制model，反过来也是
public class TankFrame extends Frame {
    static final int sc_width = PropertyMgr.getInt("scwidth"), sc_height = PropertyMgr.getInt("scheight");

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
        GameModel.getInstance().paint(g);
        
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
                    GameModel.getInstance().getMainTank().fire();
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
            if(!left && !right && !up && !down) GameModel.getInstance().getMainTank().setMoving(false); 
            else {
                GameModel.getInstance().getMainTank().setMoving(true);
                if (left) GameModel.getInstance().getMainTank().setDir(Dir.left);
                if (right) GameModel.getInstance().getMainTank().setDir(Dir.right);
                if (up) GameModel.getInstance().getMainTank().setDir(Dir.up);
                if (down) GameModel.getInstance().getMainTank().setDir(Dir.down);
            } 
        }
    }
}
