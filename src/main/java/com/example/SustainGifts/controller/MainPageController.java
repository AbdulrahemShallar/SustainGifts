package com.example.SustainGifts.controller;

import com.example.SustainGifts.securites.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class MainPageController {


    /**
     * Display a greeting message.
     *
     * @return a simple "Hello, World" message
     */
    @GetMapping("/")
    public String greeting(){
        return "Hello , World";
    }


    /**
     * Display a secured page for logged-in users.
     *
     * @param principal the authenticated user's principal details
     * @return a message indicating the user is logged in along with their email and user ID
     */
    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal principal){
        return "if i see this this, the application is logged in as USER : "+principal.getEmail()
                +" User ID: "+ principal.getUserId();
    }

    /**
     * Display an admin page for logged-in administrators.
     *
     * @param principal the authenticated admin's principal details
     * @return a message indicating the admin is logged in along with their email and user ID
     */
    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal principal){
        return "if i see this this, the application is logged in as ADMIN : "+principal.getEmail()
                +" User ID: "+ principal.getUserId();
    }
}
