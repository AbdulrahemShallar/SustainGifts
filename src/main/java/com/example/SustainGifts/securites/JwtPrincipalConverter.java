package com.example.SustainGifts.securites;


import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtPrincipalConverter {

    /**
     * Converts a decoded JWT into a UserPrincipal object.
     *
     * @param jwt the decoded JWT token
     * @return the UserPrincipal built from the token's claims
     */
    public UserPrincipal covert(DecodedJWT jwt){
        return UserPrincipal.builder()
                .userId(Integer.valueOf(jwt.getSubject()))
                .email(jwt.getClaim("e").asString())
                .authorities(extractAuthoritiesFromClaim(jwt))
                .build();
    }

    /**
     * Extracts the authorities (roles) from the JWT claim "a".
     *
     * @param jwt the decoded JWT token
     * @return a list of SimpleGrantedAuthority representing the user's roles
     */
    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt){
        var claim = jwt.getClaim("a");
        if(claim.isNull() || claim.isMissing()) {
            return List.of();
        }
            return claim.asList(SimpleGrantedAuthority.class);
    }
}
