package com.airbnb.controller;
import com.airbnb.entity.AppUser;
import com.airbnb.payload.JWTToken;
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

    // signup as user
    @PostMapping("/createUser")
    public ResponseEntity<AppUser> createUser(
            @RequestBody AppUser appUser
    ) {
        appUser.setRole("ROLE_USER");
        AppUser User = authService.createUser(appUser);
        return new ResponseEntity<>(User , HttpStatus.CREATED);
    }

    // signup as owner
    @PostMapping("/createPropertyOwner")
    public ResponseEntity<AppUser> createPropertyOwner(
            @RequestBody AppUser appUser
    ) {
        appUser.setRole("ROLE_OWNER");
        AppUser User = authService.createUser(appUser);
        return new ResponseEntity<>(User , HttpStatus.CREATED);
    }
  // managed by Manager
    @PostMapping("/createPropertyManager")
    public ResponseEntity<AppUser> createPropertyManager(
            @RequestBody AppUser appUser
    ) {
        appUser.setRole("ROLE_MANAGER");
        AppUser User = authService.createUser(appUser);
        return new ResponseEntity<>(User , HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?>signIn(
            @RequestBody LoginDto loginDto
    ){
        String token = authService.verifysignIn(loginDto);
        JWTToken jwtToken=new JWTToken();
       if (token != null) {
           jwtToken.setTokenType("JWT");
           jwtToken.setToken(token);
           return new ResponseEntity<>(jwtToken, HttpStatus.OK);
       }else {
           return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
       }
    }

//    @PostMapping("/createPropertyManager")
//    public String createpropertyManager() {
//        return "createPropertyManager";
//    }
}
