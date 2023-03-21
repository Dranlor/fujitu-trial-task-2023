package com.fujitsutask.deliveryapp.weather.exception;

public class WeatherServiceException extends RuntimeException {

    public WeatherServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
