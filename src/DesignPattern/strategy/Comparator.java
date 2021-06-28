package DesignPattern.strategy;

public interface Comparator<T> {// 传进来的时候指定T类型，（泛型）
  int compare(T o1, T o2);//把T类型替换过来
}
