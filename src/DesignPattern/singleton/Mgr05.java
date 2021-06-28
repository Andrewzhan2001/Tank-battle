package DesignPattern.singleton;

//有多线程的风险，还没有执行到new的时候另外一个线程开始检测就会出现多个instance
public class Mgr05 {
  private static volatile Mgr05 Instance;//volatile 强制将修改的变量写入主存，这样不同线程都会读到同一个值，不具备atomicity（操作不能中断，要么做完要么没做）
                                         //可见性一个线程修改了变量，其他线程立刻能看到这个变量
                                         //有序性代码执行先后顺序，volatile会导致其他线程中volatile变量缓存无效 会禁止重排
  private Mgr05(){};
  public static Mgr05 getInstance() { //保证同一时间只有一个thread执行，效率降低  synchronized(a1)同一时间只有一个tread能访问a1
    if (Instance == null) {//大部分在这里屏蔽
      synchronized (Mgr05.class) {
        if (Instance == null) {//小部分在这里屏蔽
          try {
            Thread.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          Instance = new Mgr05();
        }
      }
    }
    return Instance;
  }
  public void m() { System.out.println("m");} 
  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(//一般是传一个Runnable instance，runnable里面是有一个run方法就可以简写
                // 这样后面的大括号里直就直接写这个方法的实现
        ()->{System.out.println(Mgr05.getInstance().hashCode());}).start();// 同一个类的同一个对象hashcode不同
    }
  }
}
