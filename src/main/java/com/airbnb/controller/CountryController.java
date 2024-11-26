package com.airbnb.controller;

import com.airbnb.entity.Country;
import com.airbnb.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/addCountry")
   public ResponseEntity<Country> addCountry(
           @RequestBody Country country) {
       Country newCountry = countryService.addCountry(country);
       return new ResponseEntity<>(newCountry, HttpStatus.CREATED);
   }
}
