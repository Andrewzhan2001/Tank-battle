package DesignPattern.singleton;

//有多线程的风险，还没有执行到new的时候另外一个线程开始检测就会出现多个instance
public enum Mgr07 {
  Instance;//static不能阻止重新赋值修改指向(继承子类重新定义，static和构造里面修改)
  //enum没有构造函数没法new，没法继承
  public void m() { System.out.println("m");} 
  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(//一般是传一个Runnable instance，runnable里面是有一个run方法就可以简写
                // 这样后面的大括号里直就直接写这个方法的实现
        ()->{System.out.println(Mgr07.Instance.hashCode());}).start();// 同一个类的同一个对象hashcode不同
    }
  }
}
