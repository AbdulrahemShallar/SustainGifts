package com.example.SustainGifts.services;

import com.example.SustainGifts.converter.UserConverter;
import com.example.SustainGifts.dtos.UserDTO;
import com.example.SustainGifts.models.UserEntity;
import com.example.SustainGifts.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private UserConverter userConverter;


        /**
         * Retrieve all userEntities and convert them to DTOs.
         *
         * @return a list of UserDTOs
         */
        public List<UserDTO> getAllUsers() {
            return userRepository.findAll()
                    .stream()
                    .map(userConverter::convertToDTO)
                    .collect(Collectors.toList());
        }

        /**
         * Retrieve a userEntity by its ID and convert it to a DTO.
         *
         * @param id the ID of the userEntity
         * @return the corresponding UserDTO
         * @throws RuntimeException if no userEntity is found with the given ID
         */
        public UserDTO getUserById(Integer id) {
            return userRepository.findById(id)
                    .map(userConverter::convertToDTO)
                    .orElseThrow(() -> new RuntimeException("UserEntity not found with ID: " + id));
        }

    /**
     * Find user by email in the database.
     *
     * @param email the email of the user
     * @return an Optional containing the UserEntity, if found
     */
        public UserDTO findByEmail(String email) {
            UserEntity g = userRepository.findByEmail(email);
            UserDTO n = userConverter.convertToDTO(g);
        return userConverter.convertToDTO(userRepository.findByEmail(email));
               // .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
         }

        /**
         * Save a new userEntity by converting a UserDTO to an entity.
         *
         * @param userDTO the data transfer object for the new userEntity
         * @return the saved UserDTO
         */
        public UserDTO saveUser(UserDTO userDTO) {
            UserEntity userEntity = userConverter.convertToEntity(userDTO);
            UserEntity savedUserEntity = userRepository.save(userEntity);
            return userConverter.convertToDTO(savedUserEntity);
        }

        /**
         * Update an existing userEntity's details.
         *
         * @param id       the ID of the userEntity to update
         * @param userDTO  the updated UserDTO
         * @return the updated UserDTO
         * @throws RuntimeException if no userEntity is found with the given ID
         */
        public UserDTO updateUser(Integer id, UserDTO userDTO) {
            UserEntity existingUserEntity = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("UserEntity not found with ID: " + id));

            updateUserDetails(existingUserEntity, userDTO);
            UserEntity updatedUserEntity = userRepository.save(existingUserEntity);
            return userConverter.convertToDTO(updatedUserEntity);
        }

        /**
         * Helper method to update userEntity details from a UserDTO.
         *
         * @param userEntity the userEntity entity to update
         * @param userDTO    the source DTO containing updated details
         */
        private void updateUserDetails(UserEntity userEntity, UserDTO userDTO) {
            userEntity.setLogin(userDTO.getLogin());
            userEntity.setPassword(userDTO.getPassword());
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setRole(userDTO.getRole());
        }

        /**
         * Delete a userEntity by its ID.
         *
         * @param id the ID of the userEntity to delete
         * @return true if the userEntity was deleted, false if not found
         */
        public boolean deleteUser(Integer id) {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return true;
            }
            return false;
        }
    }