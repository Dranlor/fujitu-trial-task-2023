package com.fujitsutask.deliveryapp.httpclient;

import okhttp3.OkHttpClient;

public class HttpClientInstance {
    private static OkHttpClient instance = null;

    public static OkHttpClient getInstance() {
        if (instance == null) {
            instance = new OkHttpClient();
        }
        return instance;
    }



}
