package com.sitech.paas.asmproject.clsreplace;

/**
 * author  :mayong
 * function:
 * date    :2021/3/13
 **/
public class Test implements Human.StudyListener {
    private Human.StudyListener listener;

    public Test(Human.StudyListener listener) {
        this.listener = listener;
    }

    @Override
    public void change() {
        listener.change();
        System.out.println("哈哈哈哈成功替换change");
    }
}
