package com.sitech.paas.asmproject.okhttp3;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author  :mayong
 * function:
 * date    :2020-04-04
 **/
public class CustomCall implements Call {
    private Call impl;
    private OkHttpClient okHttpClient;
    private Request request;

    public CustomCall(OkHttpClient okHttpClient, Request request, Call call) {
        this.impl = call;
        this.okHttpClient = okHttpClient;
        this.request = request;
    }

    @Override
    public Request request() {
        return impl.request();
    }

    @Override
    public Response execute() throws IOException {
        return impl.execute();
    }

    @Override
    public void enqueue(Callback responseCallback) {
        impl.enqueue(responseCallback);
    }

    @Override
    public void cancel() {
        impl.cancel();
    }

    @Override
    public boolean isExecuted() {
        return impl.isExecuted();
    }

    @Override
    public boolean isCanceled() {
        return impl.isCanceled();
    }

    @Override
    public Call clone() {
        return impl.clone();
    }
}
