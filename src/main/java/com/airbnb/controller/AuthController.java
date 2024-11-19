package com.airbnb.controller;


import com.airbnb.entity.AppUser;
import com.airbnb.payload.LoginDto;
import com.airbnb.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/createUser")
    public ResponseEntity<AppUser> createUser(
            @RequestBody AppUser appUser
    ) {
        AppUser User = authService.createUser(appUser);
        return new ResponseEntity<>(User , HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<String>signIn(
            @RequestBody LoginDto loginDto
    ){
         boolean result = authService.verifysignIn(loginDto);
        return new ResponseEntity<>(result? "true" : "false",HttpStatus.OK);
    }
}
