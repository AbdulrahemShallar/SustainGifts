package com.example.SustainGifts.securites;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {
    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private JwtDecoder jwtDecoder;

    @Mock
    private JwtPrincipalConverter jwtPrincipalConverter;

    @Mock
    private SecurityContextHolder securityContextHolder;

    @Test
    void doFilterInternal_ValidToken() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer validToken");

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        UserPrincipal principal = UserPrincipal.builder()
                .userId(1)
                .email("test@example.com")
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
                .build();

        when(jwtDecoder.decode("validToken")).thenReturn(decodedJWT);
        when(jwtPrincipalConverter.covert(decodedJWT)).thenReturn(principal);

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(jwtDecoder).decode("validToken");
        verify(jwtPrincipalConverter).covert(decodedJWT);
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternal_InvalidToken() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer invalidToken");

        when(jwtDecoder.decode("invalidToken")).thenThrow(new RuntimeException("Invalid token"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        });

        verify(filterChain, never()).doFilter(request, response);
    }
}