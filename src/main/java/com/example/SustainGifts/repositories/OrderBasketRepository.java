package com.example.SustainGifts.repositories;

import com.example.SustainGifts.models.OrderBasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBasketRepository extends JpaRepository<OrderBasketEntity,Integer> {
}
