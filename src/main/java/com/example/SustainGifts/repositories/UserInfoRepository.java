package com.example.SustainGifts.repositories;

import com.example.SustainGifts.models.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity,Integer> {
}
