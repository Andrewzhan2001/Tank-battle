package com.mashibing.dp.state.v2;

/**
 * 当增加新的状态时非常不方便
 */

public class MM {
    String name;
    //当每一个operation都需要根据state来实现的话，就把state抽象出来在state里面实现
    MMState state = new MMHappyState();

    public void smile() {
        state.smile();
    }

    public void cry() {
        state.cry();
    }

    public void say() {
        state.say();
    }

}
