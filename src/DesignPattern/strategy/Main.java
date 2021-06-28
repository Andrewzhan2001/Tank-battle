package DesignPattern.strategy;

public class Main {
  public static void main(String[] args) {
    Cat[] a = {new Cat(1,1),new Cat(2,2),new Cat(3,3)};
    Sorter<Cat> sorter = new Sorter<>();
    sorter.sort(a,new CatWeightComparator());
    System.out.println(a[0].height);
  }
}
