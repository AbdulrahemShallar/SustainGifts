package com.example.SustainGifts.securites;


import com.example.SustainGifts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CustomUserDetailService  implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * Load a user by their email for authentication.
     *
     * @param email the email of the user to load
     * @return the UserDetails of the user
     * @throws UsernameNotFoundException if the user with the given email is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userService.findByEmail(email);
        return UserPrincipal.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole().toString())))
                .password(user.getPassword())
                .build();
    }
}
