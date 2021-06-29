package com.zhan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//spring ioc(inverse of control)让spring的控制文件里面去配置 change the control of making instance to others
public class main {
  public static void main(String[] args) {
    //context就是一个大工厂
    ApplicationContext context = new ClassPathXmlApplicationContext("app.xml");//放在classpath路径下面的xml文件的工厂

    //Driver d = (Driver)context.getBean("driver");//产生这个类的实例
    Tank t = (Tank)context.getBean("tank");
  }
  
}
