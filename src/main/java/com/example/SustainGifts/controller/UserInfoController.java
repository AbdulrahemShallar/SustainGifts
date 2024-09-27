package com.example.SustainGifts.controller;

import com.example.SustainGifts.dtos.UserInfoDTO;
import com.example.SustainGifts.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-info")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * Get UserInfo by userId.
     *
     * @param userId the ID of the user
     * @return the UserInfoDTO
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoDTO> getUserInfoByUserId(@PathVariable int userId) {
        UserInfoDTO userInfoDTO = userInfoService.getUserInfoByUserId(userId);
        return ResponseEntity.ok(userInfoDTO);
    }

    /**
     * Create or update UserInfo.
     *
     * @param userInfoDTO the UserInfoDTO
     * @return the created/updated UserInfoDTO
     */
    @PostMapping
    public ResponseEntity<UserInfoDTO> saveUserInfo(@RequestBody UserInfoDTO userInfoDTO) {
        UserInfoDTO savedUserInfo = userInfoService.saveUserInfo(userInfoDTO);
        return ResponseEntity.ok(savedUserInfo);
    }

    /**
     * Delete UserInfo by userId.
     *
     * @param userId the ID of the user
     * @return ResponseEntity
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserInfoByUserId(@PathVariable int userId) {
        userInfoService.deleteUserInfoByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}