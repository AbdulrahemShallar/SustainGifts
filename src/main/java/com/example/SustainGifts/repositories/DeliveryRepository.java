package com.example.SustainGifts.repositories;

import com.example.SustainGifts.models.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryEntity,Integer> {

}
