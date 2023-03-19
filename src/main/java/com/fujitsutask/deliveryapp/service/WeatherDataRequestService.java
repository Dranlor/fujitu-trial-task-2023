package com.fujitsutask.deliveryapp.service;

import com.fujitsutask.deliveryapp.httpclient.HttpClientInstance;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;

@Service
public class WeatherDataRequestService {

    private final OkHttpClient httpClient;

    public WeatherDataRequestService() {
        httpClient = HttpClientInstance.getInstance();
    }




}
