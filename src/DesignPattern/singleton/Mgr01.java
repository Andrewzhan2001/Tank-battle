package DesignPattern.singleton;
//加载的时候就实例化一个单例，jvm保证每个class load到内存一次（就一个实例）
public class Mgr01 {
  private static final Mgr01 Instance = new Mgr01();
  private Mgr01(){};
  public static Mgr01 getInstance(){ return Instance;}
  public void m() { System.out.println("m");} 
  public static void main(String[] args) {
    Mgr01 m1 = Mgr01.getInstance();
    Mgr01 m2 = Mgr01.getInstance();
    System.out.println(m1 == m2);
  }
}
