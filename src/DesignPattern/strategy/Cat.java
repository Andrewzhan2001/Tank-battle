package DesignPattern.strategy;

public class Cat {//implements就是让人知道这是用来实现接口
  int weight, height; //泛型指出具体的类型，编译器就能检查是不是对应（cat传到comparable里面的时候就会将cat传入compareTo)
  public Cat(int weight, int height) {
    this.weight = weight;
    this.height = height;
  }
}
