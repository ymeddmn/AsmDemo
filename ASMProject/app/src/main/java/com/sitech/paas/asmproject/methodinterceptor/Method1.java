package com.sitech.paas.asmproject.methodinterceptor;

/**
 * author  :mayong
 * function:
 * date    :2021/3/12
 **/
class Method1 {
    public void method1(){

    }
    public void method2print(){
        System.out.println("Method打印：method2print哈哈");
    }
    public static void printMethod1(Method1 method1){
        method1.method2print();
    }
}
