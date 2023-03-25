package com.fujitsutask.deliveryapp.app.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DeliveryFeeDto {
    String cityName;
    String vehicleType;
    BigDecimal totalPrice;
    String error;
}
