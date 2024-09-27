package com.example.SustainGifts.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class LoginRequest {

    private String email;
    private String password;
}
