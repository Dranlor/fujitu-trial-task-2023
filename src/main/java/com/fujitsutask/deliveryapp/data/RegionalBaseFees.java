package com.fujitsutask.deliveryapp.data;

import java.math.BigDecimal;

public enum RegionalBaseFees {
    TALLINN(new BigDecimal("3.0"), new BigDecimal("3.5"), new BigDecimal("4.0")),
    TARTU(new BigDecimal("2.5"), new BigDecimal("3.0"), new BigDecimal("3.5")),
    PARNU(new BigDecimal("2.0"), new BigDecimal("2.5"), new BigDecimal("3.0"));

    private final BigDecimal bikeFee;
    private final BigDecimal scooterFee;
    private final BigDecimal carFee;

    RegionalBaseFees(BigDecimal bikeRBF, BigDecimal scooterRBF, BigDecimal carRBF) {
        this.bikeFee = bikeRBF;
        this.carFee = carRBF;
        this.scooterFee = scooterRBF;
    }

    public BigDecimal getBikeRBF() {
        return bikeFee;
    }

    public BigDecimal getScooterRBF() {
        return scooterFee;
    }

    public BigDecimal getCarRBF() {
        return carFee;
    }
}
