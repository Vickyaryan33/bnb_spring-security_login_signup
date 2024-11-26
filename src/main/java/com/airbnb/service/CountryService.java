package com.airbnb.service;

import com.airbnb.entity.Country;
import com.airbnb.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }
}
