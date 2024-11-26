package com.airbnb.service;

import com.airbnb.entity.City;
import com.airbnb.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City addCity(City city) {
        return cityRepository.save(city);


    }
}
