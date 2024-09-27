package com.example.SustainGifts.controller;

import com.example.SustainGifts.models.LoginRequest;
import com.example.SustainGifts.models.LoginResponse;
import com.example.SustainGifts.services.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @Test
    void login_Success() {

        // Arrange
        LoginRequest loginRequest = LoginRequest.builder()
                .email("test@example.com")
                .password("test")
                .build();

        LoginResponse loginResponse  = LoginResponse.builder()
                .accessToken("mockToken123")
                .build();


        when(authService.attemptLogin(anyString(), anyString())).thenReturn(loginResponse);

        // Act
        ResponseEntity<LoginResponse> response = authController.login(loginRequest);


        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("mockToken123", response.getBody().getAccessToken());
        verify(authService).attemptLogin(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @Test
    void login_Failure() {
        // Arrange
        LoginRequest loginRequest = LoginRequest.builder()
                .email("test@example.com")
                .password("wrongpassword")
                .build();

        when(authService.attemptLogin(anyString(), anyString())).thenThrow(new RuntimeException("Invalid credentials"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            authController.login(loginRequest);
        });

        verify(authService).attemptLogin(loginRequest.getEmail(), loginRequest.getPassword());
    }
}