package com.airbnb;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class A {
    public static void main(String[] args) {

        //1st method to encode the password
//        BCryptPasswordEncoder en = new BCryptPasswordEncoder();
//        System.out.println(en.encode("v123"));

        //2d method to encode the password
        String  pass = BCrypt.hashpw("v123", BCrypt.gensalt(10));
    System.out.println(pass);
    }
}
