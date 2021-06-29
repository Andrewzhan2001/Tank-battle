package DesignPattern.factory;

//普通工厂先创建一个工厂类里面有很多的工厂交通，食物...
//交通下面都是从movable继承下来的对象
//添加新的产品（工具）只需要工具类型，工具工厂和背个工具的方法
//不然abstract这样每个之前写好的factory都得加
public class Main {
  public static void main(String[] args) {
      VehicleFactory f = new VehicleFactory();
      Car c = (Car)f.produceVehicle(Car.class);
      c.go();
  }
}
