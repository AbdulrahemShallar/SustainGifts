package com.example.SustainGifts.converter;

import com.example.SustainGifts.dtos.UserDTO;
import com.example.SustainGifts.models.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    /**
     * Converts a UserEntity entity to a UserDTO.
     *
     * @param userEntity the UserEntity entity to convert
     * @return the converted UserDTO
     */
    public UserDTO convertToDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .id(userEntity.getId())
                .login(userEntity.getLogin())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .role(userEntity.getRole())
                .build();
    }

    /**
     * Converts a UserDTO to a UserEntity entity.
     *
     * @param userDTO the UserDTO to convert
     * @return the converted UserEntity entity
     */
    public UserEntity convertToEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .id(userDTO.getId())
                .login(userDTO.getLogin())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword()) // Assuming password is also passed in DTO
                .role(userDTO.getRole())
                .build();
    }

    /**
     * Converts a list of UserEntity entities to a list of UserDTOs.
     *
     * @param users the list of UserEntity entities to convert
     * @return the list of converted UserDTOs
     */
    public List<UserDTO> convertToDTOList(List<UserEntity> users) {
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Converts a list of UserDTOs to a list of UserEntity entities.
     *
     * @param userDTOs the list of UserDTOs to convert
     * @return the list of converted UserEntity entities
     */
    public List<UserEntity> convertToEntityList(List<UserDTO> userDTOs) {
        return userDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}