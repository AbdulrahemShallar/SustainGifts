package com.example.SustainGifts.securites;


import org.springframework.security.authentication.AbstractAuthenticationToken;



public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {

    private final UserPrincipal principal;

    /**
     * Constructs an authenticated token for the given UserPrincipal.
     *
     * @param principal the authenticated UserPrincipal
     */
    public UserPrincipalAuthenticationToken(UserPrincipal principal) {
        super(principal.getAuthorities());
        setAuthenticated(true);
        this.principal = principal;
    }

    /**
     * Returns the credentials of the token, which are not used in this case.
     *
     * @return null as credentials are not required
     */
    @Override
    public Object getCredentials() {
        return null;
    }

    /**
     * Returns the principal (the authenticated user).
     *
     * @return the authenticated UserPrincipal
     */
    @Override
    public UserPrincipal getPrincipal() {
        return principal;
    }
}
