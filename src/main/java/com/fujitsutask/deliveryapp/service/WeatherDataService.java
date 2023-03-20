package com.fujitsutask.deliveryapp.service;

import com.fujitsutask.deliveryapp.httpclient.HttpClientInstance;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherDataService {

    private final OkHttpClient httpClient;

    @Autowired
    public WeatherDataService(OkHttpClient client) {
        httpClient = client;
    }




}
