package com.example.SustainGifts.controller;

import com.example.SustainGifts.dtos.UserDTO;
import com.example.SustainGifts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Get all users.
     *
     * @return a list of all UserDTOs
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Get a specific user by ID.
     *
     * @param id the ID of the user to retrieve
     * @return the UserDTO corresponding to the specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }


    /**
     * Get a user by email.
     *
     * @param email the email of the user to retrieve
     * @return the UserDTO and HTTP status 200 (OK)
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        UserDTO userDTO = userService.findByEmail(email);
        return ResponseEntity.ok(userDTO);
    }

    /**
     * Create a new user.
     *
     * @param userDTO the UserDTO representing the new user
     * @return the created UserDTO
     */
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.saveUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Update an existing user.
     *
     * @param id      the ID of the user to update
     * @param userDTO the updated UserDTO
     * @return the updated UserDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Delete a user by ID.
     *
     * @param id the ID of the user to delete
     * @return a ResponseEntity indicating the outcome of the operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}