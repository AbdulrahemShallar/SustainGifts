package com.example.SustainGifts.converter;

import com.example.SustainGifts.dtos.UserInfoDTO;
import com.example.SustainGifts.models.UserEntity;
import com.example.SustainGifts.models.UserInfoEntity;
import org.springframework.stereotype.Component;

@Component
public class UserInfoConverter {

    /**
     * Converts a UserInfoEntity to a UserInfoDTO.
     *
     * @param userInfoEntity the UserInfoEntity to convert
     * @return the converted UserInfoDTO
     */
    public UserInfoDTO convertToDTO(UserInfoEntity userInfoEntity) {
        return UserInfoDTO.builder()
                .userId(userInfoEntity.getUserId())
                .name(userInfoEntity.getName())
                .surname(userInfoEntity.getSurname())
                .phone(userInfoEntity.getPhone())
                .build();
    }

    /**
     * Converts a UserInfoDTO to a UserInfoEntity.
     *
     * @param userInfoDTO the UserInfoDTO to convert
     * @param userEntity the related UserEntity
     * @return the converted UserInfoEntity
     */
    public UserInfoEntity convertToEntity(UserInfoDTO userInfoDTO, UserEntity userEntity) {
        return UserInfoEntity.builder()
                .userId(userInfoDTO.getUserId())
                .name(userInfoDTO.getName())
                .surname(userInfoDTO.getSurname())
                .phone(userInfoDTO.getPhone())
                .userEntity(userEntity)
                .build();
    }
}
