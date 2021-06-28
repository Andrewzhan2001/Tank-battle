package DesignPattern.singleton;

//有多线程的风险，还没有执行到new的时候另外一个线程开始检测就会出现多个instance
public class Mgr06 {
  private Mgr06(){};
  private static class Mgr06Holder {//之家在mgr06，holder不会初始化
    private final static Mgr06 Instance = new Mgr06();
  }
  public static Mgr06 getInstance() {
    return Mgr06Holder.Instance;
  }



  public void m() { System.out.println("m");} 
  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(//一般是传一个Runnable instance，runnable里面是有一个run方法就可以简写
                // 这样后面的大括号里直就直接写这个方法的实现
        ()->{System.out.println(Mgr06.getInstance().hashCode());}).start();// 同一个类的同一个对象hashcode不同
    }
  }
}
