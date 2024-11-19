package com.airbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityConfig {
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http
    )  throws Exception {

        // url security will be closed and will be open now
        // **** importantformula to write the code:-haap  and h(cd)^2***********
 //    h(cd)^2
        http.csrf().disable().csrf().disable();
 //    haap
        http.authorizeHttpRequests().anyRequest().permitAll();
        return http.build();

    }
}
