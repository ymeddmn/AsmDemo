package com.sitech.paas.asmproject;

/**
 * author  :mayong
 * function:
 * date    :2020-03-29
 **/
public class Demo {
    String a = "哈哈";
    String b = "哈哈";

    public String print() {
        p1("哈哈哈哈p1");
        new Demo().print2();
        p2();
        return "print结果";

    }

    public String p() {
        return "哈哈p";
    }

    public void p1(String p) {
        System.out.println(p);
    }

    public void p2() {

    }

    public void print2() {
        System.out.println("哈哈哈print2");
    }

    public static void m() {
        System.out.println("打印插入代码方法");
    }
}
