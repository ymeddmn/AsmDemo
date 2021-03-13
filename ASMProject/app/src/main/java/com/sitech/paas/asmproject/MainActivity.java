package com.sitech.paas.asmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.sitech.paas.asmproject.anim.Client;
import com.sitech.paas.asmproject.clsreplace.Human;
import com.sitech.paas.asmproject.clsreplace.Human2;
import com.sitech.paas.asmproject.clsreplace.Test;
import com.sitech.paas.asmproject.clsreplace.Test2;
import com.sitech.paas.asmproject.methodinterceptor.MethodInterceptor;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new Client().eat();
//                new MethodInterceptor().print();

                //-----------------这部分代码测试的时候位置不能动，否则项目就无法正常运行--------------
//                Human human = new Human();
//                Human.StudyListener studyListener = new Human.StudyListener() {
//                    @Override
//                    public void change() {
//                        System.out.println("内部类打印change");
//                    }
//                };
//                human.setListener(studyListener);
//                human.study();
                //-----------------这部分代码测试的时候位置不能动，否则项目就无法正常运行--------------

                study2();

            }
        });

    }

    private void study2() {
        Human2 human = new Human2();
        human.setListener2(new Human2.StudyListener() {
            @Override
            public void change() {
                System.out.println("打印human2");
            }
        });
        human.study2();
    }
}