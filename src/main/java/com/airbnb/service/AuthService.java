package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.exception.UserExists;
import com.airbnb.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private AppUserRepository appUserRepository;

    public AuthService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser createUser(AppUser appUser) {
        Optional<AppUser> opemail =  appUserRepository.findByEmail(appUser.getEmail());
     if(opemail.isPresent()){
     throw  new UserExists("Email already exists");
        }

        Optional<AppUser> opuser =  appUserRepository.findByUsername(appUser.getUsername());
        if(opuser.isPresent()){
            throw  new UserExists("User already exists");
        }

        //encrpted password
        String pass = BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt(10));
        appUser.setPassword(pass);
        return appUserRepository.save(appUser);

    }
}
