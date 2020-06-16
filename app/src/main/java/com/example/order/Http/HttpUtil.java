package com.example.order.Http;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/*
访问网络并通过回调最终的请求结果回调到okhttp3.Callback中
 */
public class HttpUtil {

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();

        client.newCall(request).enqueue(callback);
    }

}
