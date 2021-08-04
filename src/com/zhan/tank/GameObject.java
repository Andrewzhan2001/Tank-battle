package com.zhan.tank;

import java.awt.Graphics;

public abstract class GameObject {
  public int x;
  public int y;
  public abstract int paint(Graphics g);
  public abstract int getWidth();
  public abstract int getHeight();
} 
