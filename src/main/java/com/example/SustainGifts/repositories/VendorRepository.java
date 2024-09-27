package com.example.SustainGifts.repositories;

import com.example.SustainGifts.models.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, Integer> {
}
