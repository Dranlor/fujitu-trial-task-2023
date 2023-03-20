package com.fujitsutask.deliveryapp.httpclient;

import okhttp3.OkHttpClient;

/**
 * Singleton class used to retrieve the object that can be used to create HTTP requests.
 * <p/>
 * The class instance can be gotten through the {@code HttpClientInstance.getInstance()} method.
 */
public class HttpClientInstance {
    private static OkHttpClient instance = null;

    /**
     * Returns the instance used to make HTTP requests.
     * @return Instance of class used for HTTP communication.
     */
    public static OkHttpClient getInstance() {
        if (instance == null) {
            instance = new OkHttpClient();
        }
        return instance;
    }



}
