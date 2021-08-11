package DesignPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;//reflection 通过二进制字节码分析类的属性和方法
import java.util.Random;
//java 动态语言，在运行的时候改变类里面的属性和方法（删除属性和方法）
//java load class 用双亲委派机制，从最基本的（object)来开始load，这样就算下面有class重写了基本的class也不会使底层逻辑报错
//java instrument来是在jdk把class load到内存的时候会拦截并直接修改二进制码
interface Movable{
  void move();
}

public class Tank implements Movable {;

  @Override
  public void move() {
    System.out.println("tank move");
    Thread.sleep(new Random().nextInt(10000));
  }
  
}
public class LogHander implements InvocationHandler{
  Tank tank;
  public LogHander(Tank tank) {
    this.tank= tank;
  }

  @Override
  //method就是用move方法来创建的对象，method.invoke就会执行第一个参数的move方法
  //method是你调用了什么方法（move）因为可以实现很多的接口，会有不同的方法（被代理对象的方法）
  //args是你往这个方法里传了什么参数
  //proxy是生成的代理对象（m）
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    //这里就是调用坦克的move方法
    //第一个是调用哪个对象的move方法
    Object o = method.invoke(tank, args);
    return null;
  }
}

public class proxydynamic {
  public static void main(String[] args) {
    Tank tank = new Tank();
    System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true"); //把代理产生器这个savegeneratedfile这个属性设成true
    //他会根据list of interface 得到创建什么方法，在这个方法（move）里会调用handler里面的invoke方法
    // 第一个是代理的对象，第二个是实现了那些接口，参数传进来的时候交给loghander(一个invocationhandler)来处理
    Movable m = (Movable) Proxy.newProxyInstance(Tank.class.getClassLoader(), new Class[]{Movable.class},new LogHander(tank));
    m.move();
  }
}
