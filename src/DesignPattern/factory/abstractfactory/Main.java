package DesignPattern.factory.abstractfactory;

//这个方法要是添加一种新产品的时候需要在magic,modern里都要添加
//创建新的产品族的时候只需要添加新的工厂就行
public class Main {
  public static void main(String[] args) {
      Abstractfactory f = new ModernFactory();//在这里改变对应的factory就能改变产品族
      Vehicle c = f.createVehicle();
      c.go();
      Weapon w = f.createWeapon();
      w.shoot();
  }
}
