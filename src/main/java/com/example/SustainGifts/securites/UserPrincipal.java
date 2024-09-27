package com.example.SustainGifts.securites;

import com.example.SustainGifts.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Builder
public class UserPrincipal implements UserDetails {
    private final Integer userId;
    private final String email;

    @JsonIgnore
    private final String password;

    private final Role role;
    private final  Collection<? extends  GrantedAuthority> authorities;


    /**
     * Returns the authorities (roles) granted to the user.
     *
     * @return the authorities assigned to the user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the user's password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username (email in this case) used to authenticate the user.
     *
     * @return the user's email
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Indicates whether the user's account has expired.
     * Always returns true, meaning the account is non-expired.
     *
     * @return true, if the account is non-expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
//        return UserDetails.super.isAccountNonExpired();
    }

    /**
     * Indicates whether the user is locked or unlocked.
     * Always returns true, meaning the account is non-locked.
     *
     * @return true, if the account is non-locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
//        return UserDetails.super.isAccountNonLocked();
    }

    /**
     * Indicates whether the user's credentials have expired.
     * Always returns true, meaning the credentials are non-expired.
     *
     * @return true, if the credentials are non-expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
//        return UserDetails.super.isCredentialsNonExpired();
    }

    /**
     * Indicates whether the user is enabled or disabled.
     * Always returns true, meaning the user is enabled.
     *
     * @return true, if the user is enabled
     */
    @Override
    public boolean isEnabled() {
        return true;
//        return UserDetails.super.isEnabled();
    }
}
