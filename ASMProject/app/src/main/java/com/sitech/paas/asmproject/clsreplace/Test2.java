package com.sitech.paas.asmproject.clsreplace;

/**
 * author  :mayong
 * function:
 * date    :2021/3/13
 **/
public class Test2 implements Human2.StudyListener {
    Human2.StudyListener listener;

    public Test2(Human2.StudyListener listener) {
        this.listener = listener;
    }

    @Override
    public void change() {
        System.out.println("打印human2代理内如");
        if(listener!=null){
            listener.change();
        }
    }
}
