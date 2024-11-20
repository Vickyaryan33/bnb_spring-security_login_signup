package com.airbnb.service;
import com.airbnb.entity.AppUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int tokenExpireTime;

    private Algorithm algorithm;


    @PostConstruct // this method comes from hibernate
    public void PostConstruct() throws Exception {

//        System.out.println("algorithmKey : " + algorithmKey);
//        System.out.println("issuer : " + issuer);
//        System.out.println("tokenExpireTime : " + tokenExpireTime);
        algorithm = Algorithm.HMAC256(algorithmKey);


    }

    //  generate the token
    public String generateToken(AppUser appUser) {

            return JWT.create()
                    .withClaim("username", appUser.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + tokenExpireTime))
                    .withIssuer(issuer)
                    .sign(algorithm);  // sort trick computer eng is sign the token

    }
}
