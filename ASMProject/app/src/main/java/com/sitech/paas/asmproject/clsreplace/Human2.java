package com.sitech.paas.asmproject.clsreplace;

/**
 * author  :mayong
 * function:
 * date    :2021/3/13
 **/
public class Human2 {
    private StudyListener listener2;

    public void study2() {
        if (listener2 != null) {
            listener2.change();
        }
    }
    public void setListener2(StudyListener listener) {
        this.listener2 = listener;
    }


    public interface StudyListener {
        void change();
    }
}
