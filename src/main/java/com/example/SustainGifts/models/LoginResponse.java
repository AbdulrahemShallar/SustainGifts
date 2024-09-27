package com.example.SustainGifts.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse  {
    private final String accessToken;
}
