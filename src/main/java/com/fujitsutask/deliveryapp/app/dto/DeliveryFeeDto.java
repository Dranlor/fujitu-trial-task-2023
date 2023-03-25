package com.fujitsutask.deliveryapp.app.dto;

import com.fujitsutask.deliveryapp.app.exception.DeliveryFeeException;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DeliveryFeeDto {
    String cityName;
    String vehicleType;
    BigDecimal totalPrice;
    DeliveryFeeException error;
}
