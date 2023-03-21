package com.fujitsutask.deliveryapp.app.repository;

import com.fujitsutask.deliveryapp.app.model.CitiesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends JpaRepository<CitiesModel, Long> {

}
