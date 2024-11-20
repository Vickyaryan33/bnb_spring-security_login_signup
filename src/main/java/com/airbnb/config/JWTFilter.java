package com.airbnb.config;

import com.airbnb.repository.AppUserRepository;
import com.airbnb.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
// make a special class so using component
@Component
public class JWTFilter extends OncePerRequestFilter {
    private JWTService jwtService;
    private AppUserRepository appUserRepository;

    public JWTFilter(JWTService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
//        System.out.println(token);
        if(token != null && token.startsWith("Bearer ")) {
            String tokenVal = token.substring(8, token.length()-1);
//         System.out.println(tokenVal);
       String username = jwtService.getUserName(tokenVal);
        System.out.println(username);
        }
        // Executes the next filter in the chain.
        filterChain.doFilter(request, response);
    }
}
