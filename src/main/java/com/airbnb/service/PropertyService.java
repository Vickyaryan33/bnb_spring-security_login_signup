package com.airbnb.service;

import com.airbnb.entity.Property;
import com.airbnb.repository.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {
    private PropertyRepository propertyRepository;


    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }
}
