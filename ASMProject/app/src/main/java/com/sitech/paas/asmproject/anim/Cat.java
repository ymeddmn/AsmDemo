package com.sitech.paas.asmproject.anim;

/**
 * author  :mayong
 * function:
 * date    :2021/3/12
 **/
class Cat extends Animinal{
    @Override
    public void eat(String name) {
        System.out.println("猫吃食物:"+name);
    }
}
