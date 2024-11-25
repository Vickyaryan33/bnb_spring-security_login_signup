package com.airbnb.service;
import com.airbnb.entity.AppUser;
import com.airbnb.exception.UserExists;
import com.airbnb.payload.LoginDto;
import com.airbnb.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    private JWTService jwtService;
private AppUserRepository appUserRepository;
    public AuthService(JWTService jwtService, AppUserRepository appUserRepository) {

        this.jwtService = jwtService;
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
        //encripted password using BCrypt
        String pass = BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt(10));
        appUser.setPassword(pass);

        return appUserRepository.save(appUser);
    }

    public String verifysignIn(LoginDto loginDto) {
//        Optional<AppUser> opuser =  appUserRepository.findByUsername(loginDto.getUsername());

        Optional<AppUser> opuser =  appUserRepository.findByUsernameOrEmail(loginDto.getUsername(), loginDto.getUsername());
        if(opuser.isPresent()){
            AppUser user = opuser.get();
            if(BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
               return jwtService.generateToken(user);



            }
        }
        return null;
    }
}
