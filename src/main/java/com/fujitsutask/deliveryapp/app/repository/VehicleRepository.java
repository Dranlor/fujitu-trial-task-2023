package com.fujitsutask.deliveryapp.app.repository;

import com.fujitsutask.deliveryapp.app.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleModel, Long> {
}
