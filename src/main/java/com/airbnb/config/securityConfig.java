package com.airbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
public class securityConfig {
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    private final JWTFilter jwtFilter;

    public securityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http
    )  throws Exception {

        // url security will be closed and will be open now
        // **** importantformula to write the code:-haap  and h(cd)^2***********
 //    h(cd)^2
        http.csrf().disable().csrf().disable();


 //    haap


      // this url open for all
//         http.authorizeHttpRequests().anyRequest().permitAll();

        // JWT Filter before authorizationFilter run first
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);

        // then after filter the url
        http.authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/createUser","/api/v1/auth/createPropertyOwner","/api/v1/auth/login")
                .permitAll()
                .requestMatchers("/api/v1/auth/createPropertyOwner").hasAnyRole("OWNER","ADMIN","MANAGER")
                .requestMatchers("/api/v1/auth/createPropertyManager").hasRole("ADMIN")
                .anyRequest().authenticated();


            return http.build();

    }

    
}
