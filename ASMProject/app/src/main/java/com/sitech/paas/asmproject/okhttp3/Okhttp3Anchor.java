package com.sitech.paas.asmproject.okhttp3;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * author  :mayong
 * function:
 * date    :2020-04-04
 **/
public class Okhttp3Anchor {
    public static CustomCall newCall(OkHttpClient okHttpClient, Request request){
        Call call = okHttpClient.newCall(request);
        CustomCall customCall = new CustomCall(okHttpClient, request, call);
        return customCall;
    }
}
