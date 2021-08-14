import java.lang.reflect.Method;
import java.util.Random;

//需要cglib
//cglib会生成一个被代理类的子类
public class Main {
  public static void main(String[] args) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(Tank.class);
    enhancer.setCallback(new TimeMethodInterceptor());
    Tank t = (Tank)enhancer.create();
    t.move();//会调用TimeMethodInterceptor里的Intercept语句
  }
}

public class Tank {
  public void move(){
    System.out.println("Tank moveing");
    Thread.sleep(new Random().nextInt(10000));
  }
}

class TimeMethodInterceptor implements MethodInterceptor{
  @Override
  //objects,调用move()导入的参数
  //method原move的引用，直接调用会无限循环（一个新的t的move（））
  //methodproxy为代理方法的引用
  public Object Intercept (Object o, Method method, Object[] objects, MethodProxy methodProxy) {
    System.out.println("before");
    Object result = null;
    //调用原来的方法
    result = methodProxy.invokeSuper(o, objects);
    System.out.println("after");
    return result;
  }
  
}
