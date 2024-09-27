package com.example.SustainGifts.services;

import com.example.SustainGifts.models.LoginResponse;
import com.example.SustainGifts.securites.JwtIssuer;
import com.example.SustainGifts.securites.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtIssuer jwtIssuer;

    private final AuthenticationManager authenticationManager;


    /**
     * Attempts to authenticate a user using their email and password, and issues a JWT token if successful.
     *
     * @param email    the user's email
     * @param password the user's password
     * @return a LoginResponse containing the generated JWT access token
     */
    public LoginResponse attemptLogin(String email,String password){
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );

        // Set the authentication in the security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Retrieve the authenticated user principal
        var principle = (UserPrincipal) authentication.getPrincipal();

        // Get the user's roles (authorities) as a list of strings
        var roles = principle.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        // Issue a JWT token for the authenticated user
        var token = jwtIssuer.issue(principle.getUserId(),principle.getEmail(),roles);

        // Return the login response containing the JWT access token
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }
}
