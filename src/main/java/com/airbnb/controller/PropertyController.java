package com.airbnb.controller;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import com.airbnb.entity.Property;
import com.airbnb.repository.CityRepository;
import com.airbnb.repository.CountryRepository;
import com.airbnb.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyService propertyService;
    private CountryRepository countryRepository;
    private CityRepository cityRepository;

    public PropertyController(PropertyService propertyService, CountryRepository countryRepository, CityRepository cityRepository) {
        this.propertyService = propertyService;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }
    @PostMapping
    public ResponseEntity<?> addProperty(
           @RequestBody Property property , @RequestParam("cityId") long cityId , @RequestParam("countryId") long countryId) {

        City cityId1 = cityRepository.findById(cityId).get();
        Country country1  =  countryRepository.findById(countryId).get();
        property.setCity(cityId1);
        property.setCountry(country1);
        Property property1 = propertyService.addProperty(property);
        return new ResponseEntity<>(property1, HttpStatus.CREATED);
    }
}
