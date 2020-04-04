package com.sitech.paas.asmproject;

import java.io.IOException;

import okhttp3.*;

/**
 * author  :mayong
 * function:
 * date    :2020-03-29
 **/
public class Okhttp3 {



    public void request() {

        String url = "https://www.jianshu.com/p/da4a806e599b";
//        val url = "http://eip.tamshub.com"
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("onResponse");
            }
        });

    }
//    public static   CustomOk3Callback getCallback(Callback callback){
//        CustomOk3Callback ok3Callback = new CustomOk3Callback(callback);
//        return ok3Callback;
//    }
}
