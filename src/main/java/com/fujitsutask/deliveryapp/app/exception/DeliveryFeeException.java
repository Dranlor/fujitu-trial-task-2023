package com.fujitsutask.deliveryapp.app.exception;

/**
 * Exception thrown in DeliveryFeeService.
 */
public class DeliveryFeeException extends RuntimeException {

    public enum Reason {INVALID_VEHICLE_TYPE, UNFIT_CONDITIONS_FOR_VEHICLE_TYPE, INVALID_VEHICLE_ID, INVALID_CITY_ID,
        NO_WEATHER_ENTRY}

    private final String message;
    private final Reason reason;

    public DeliveryFeeException(String message, Reason reason) {
        super(message);
        this.message = message;
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Reason getReason() {
        return reason;
    }
}
