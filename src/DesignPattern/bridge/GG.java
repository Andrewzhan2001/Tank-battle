package DesignPattern.bridge;

public class GG {
    public void chase(MM mm) {
        //通过用聚合来代替继承
        //不然的话各种warmflower，warmbook这样会产生很多的类
        Gift g = new WarmGift(new Flower());
        give(mm, g);
    }

    public void give(MM mm, Gift g) {
        System.out.println(g + "gived!");
    }

}
