package DesignPattern.singleton;

//有多线程的风险，还没有执行到new的时候另外一个线程开始检测就会出现多个instance
public class Mgr03 {
  private static Mgr03 Instance;
  private Mgr03(){};
  public static Mgr03 getInstance() { 
    if (Instance == null) {//等于null在初始化
      try {
        Thread.sleep(1);//不然线程执行的太快
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      Instance = new Mgr03();
    }
    return Instance;
  }
  public void m() { System.out.println("m");} 
  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(//一般是传一个Runnable instance，runnable里面是有一个run方法就可以简写
                // 这样后面的大括号里直就直接写这个方法的实现
        ()->{System.out.println(Mgr03.getInstance().hashCode());}).start();// 同一个类的同一个对象hashcode不同
    }
  }
}
