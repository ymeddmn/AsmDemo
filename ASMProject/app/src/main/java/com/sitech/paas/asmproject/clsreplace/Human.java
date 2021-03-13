package com.sitech.paas.asmproject.clsreplace;

/**
 * author  :mayong
 * function:
 * date    :2021/3/13
 **/
public class Human {
    private StudyListener listener;

    public void study() {
        if (listener != null) {
            listener.change();
        }
    }

    public void setListener(StudyListener listener) {
        this.listener = listener;
    }

    public interface StudyListener {
        void change();
    }
}
