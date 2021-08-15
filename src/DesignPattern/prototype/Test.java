package DesignPattern.prototype;

/**
 * 就string不需要做深克隆，stirng是建在常量池里的，所以当我们修改当前的string他会
 * 在池里建另外一个string并赋值过去，不影响浅克隆出来的string
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Person p1 = new Person();
        Person p2 = (Person)p1.clone();
        System.out.println("p1.loc == p2.loc? " + (p1.loc == p2.loc));

        p1.loc.street.reverse();
        System.out.println(p2.loc.street);
    }
}

class Person implements Cloneable {
    int age = 8;
    int score = 100;

    Location loc = new Location(new StringBuilder("bj"), 22);
    @Override
    public Object clone() throws CloneNotSupportedException {
        Person p = (Person)super.clone(); // make a copy of this object
        p.loc = (Location)loc.clone();
        return p;
    }
}

class Location implements Cloneable {
    //stringbuilder 属于类，需要深克隆
    StringBuilder street;
    int roomNo;

    @Override
    public String toString() {
        return "Location{" +
                "street='" + street + '\'' +
                ", roomNo=" + roomNo +
                '}';
    }

    public Location(StringBuilder street, int roomNo) {
        this.street = street;
        this.roomNo = roomNo;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
