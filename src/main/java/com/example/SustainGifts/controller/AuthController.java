package com.example.SustainGifts.controller;

import com.example.SustainGifts.models.LoginRequest;
import com.example.SustainGifts.models.LoginResponse;
import com.example.SustainGifts.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Attempt to log in with the provided credentials.
     *
     * @param request the login request containing the email and password
     * @return the response containing authentication details
     */
    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request){
        return authService.attemptLogin(request.getEmail(),request.getPassword());
    }
}
