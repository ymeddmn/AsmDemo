package com.sitech.paas.asmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    TestInterface testInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        testInterface=new Okhttp3();
//        testInterface.print();
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               new Thread(new Runnable() {
//                   @Override
//                   public void run() {
//                       new Okhttp3().request();
//
//                   }
//               }).start();
                String print = new Demo().print();
//                System.out.println(print);
            }
        });
    }
}

