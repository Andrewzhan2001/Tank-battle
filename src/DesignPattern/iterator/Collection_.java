package DesignPattern.iterator;

public interface Collection_<E> {
    void add(E o);
    int size();

    Iterator_ iterator();
}
