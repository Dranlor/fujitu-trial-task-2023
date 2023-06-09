package com.fujitsutask.deliveryapp.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Maps to EXTRAFEES table in H2 database.
 */
@Data
@Entity
@Table(name = "EXTRAFEES")
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VEHICLETYPE", unique = true, nullable = false)
    private String vehicleType;

    @Column(name = "ATEF_FREEZING")
    private BigDecimal atefFreezing;

    @Column(name = "ATEF_MODERATE")
    private BigDecimal atefModerate;

    @Column(name = "WSEF_MODERATE")
    private BigDecimal wsefModerate;

    @Column(name = "WPEF_SNOW_SLEET")
    private BigDecimal wpefSnowSleet;

    @Column(name = "WPEF_RAIN")
    private BigDecimal wpefRain;

    @Override
    public String toString() {
        return "VehicleModel{" +
                "id=" + id +
                ", vehicleType='" + vehicleType + '\'' +
                ", atefFreezing=" + atefFreezing +
                ", atefModerate=" + atefModerate +
                ", wsefModerate=" + wsefModerate +
                ", wpefSnowSleet=" + wpefSnowSleet +
                ", wpefRain=" + wpefRain +
                '}';
    }
}
