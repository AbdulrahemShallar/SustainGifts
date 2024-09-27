package com.example.SustainGifts.securites;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class JwtIssuer {

    @Autowired
    private JwtProperties properties;

    /**
     * Issues a JWT token for a user with the given ID, email, and roles.
     *
     * @param userId the ID of the user
     * @param email  the email of the user
     * @param roles  the list of roles assigned to the user
     * @return the signed JWT token as a String
     */
    public String issue(Integer userId, String email, List<String> roles){
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
                .withClaim("e",email)
                .withClaim("a",roles)
                .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }
}
