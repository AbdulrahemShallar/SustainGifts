package com.example.SustainGifts.dtos;

import com.example.SustainGifts.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Integer id;
    private String login;
    private String email;
    private String password;
    private Role role;
}
