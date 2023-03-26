package com.fujitsutask.deliveryapp.weather.exception;

/**
 * Exception thrown by the WeatherService.
 */
public class WeatherServiceException extends RuntimeException {

    public WeatherServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
