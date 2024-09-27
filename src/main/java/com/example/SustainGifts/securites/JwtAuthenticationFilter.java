package com.example.SustainGifts.securites;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    private JwtPrincipalConverter jwtPrincipalConverter;

    /**
     * Filters each request to authenticate the user based on JWT token.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @param filterChain the filter chain to continue processing the request
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       extractTokenFromRequest(request)
               .map(jwtDecoder::decode)
               .map(jwtPrincipalConverter::covert)
               .map(UserPrincipalAuthenticationToken::new)
               .ifPresent(authentication -> SecurityContextHolder.getContext().setAuthentication(authentication));
        filterChain.doFilter(request,response);
    }

    /**
     * Extracts the JWT token from the Authorization header of the request.
     *
     * @param request the HTTP request
     * @return an Optional containing the JWT token, or an empty Optional if not found
     */
    private Optional<String> extractTokenFromRequest(HttpServletRequest request){
        var token = request.getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer "))
            return Optional.of(token.substring(7));
        return Optional.empty();

    }
}
