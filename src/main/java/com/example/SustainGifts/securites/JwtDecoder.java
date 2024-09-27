package com.example.SustainGifts.securites;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtDecoder {

    @Autowired
    private JwtProperties properties;

    /**
     * Decodes and verifies a JWT token using the secret key.
     *
     * @param token the JWT token to decode
     * @return the decoded JWT token (DecodedJWT)
     */
    public DecodedJWT decode(String token){
        return JWT.require(Algorithm.HMAC256(properties.getSecretKey()))
                .build()
                .verify(token);
    }
}
