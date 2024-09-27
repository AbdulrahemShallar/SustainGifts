package com.example.SustainGifts.services;

import com.example.SustainGifts.converter.UserInfoConverter;
import com.example.SustainGifts.dtos.UserInfoDTO;
import com.example.SustainGifts.models.UserEntity;
import com.example.SustainGifts.models.UserInfoEntity;
import com.example.SustainGifts.repositories.UserInfoRepository;
import com.example.SustainGifts.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoConverter userInfoConverter;

    /**
     * Get UserInfo by userId.
     *
     * @param userId the ID of the user
     * @return the UserInfoDTO
     */
    public UserInfoDTO getUserInfoByUserId(int userId) {
        return userInfoRepository.findById(userId)
                .map(userInfoConverter::convertToDTO)
                .orElseThrow(() -> new RuntimeException("UserInfo not found for userId: " + userId));
    }

    /**
     * Create or update UserInfo.
     *
     * @param userInfoDTO the UserInfoDTO to save
     * @return the saved UserInfoDTO
     */
    public UserInfoDTO saveUserInfo(UserInfoDTO userInfoDTO) {
        // Fetch associated UserEntity
        UserEntity userEntity = userRepository.findById(userInfoDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userInfoDTO.getUserId()));

        // Convert DTO to Entity
        UserInfoEntity userInfoEntity = userInfoConverter.convertToEntity(userInfoDTO, userEntity);

        // Save UserInfo
        UserInfoEntity savedEntity = userInfoRepository.save(userInfoEntity);

        return userInfoConverter.convertToDTO(savedEntity);
    }

    /**
     * Delete UserInfo by userId.
     *
     * @param userId the ID of the user
     */
    public void deleteUserInfoByUserId(int userId) {
        userInfoRepository.deleteById(userId);
    }
}