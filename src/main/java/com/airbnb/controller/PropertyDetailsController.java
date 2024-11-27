package com.airbnb.controller;
import com.airbnb.entity.Property;
import com.airbnb.repository.PropertyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/propertydetails")
public class PropertyDetailsController {
    private PropertyRepository propertyRepository;

    public PropertyDetailsController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @GetMapping("/searchproperty")
    public List<Property> SearchProperty(
            // http://localhost:8080/api/v1/propertydetails/searchproperty?CityName=patna  yaha CityName pass krna h
//            @RequestParam("CityName") String CityName
            @RequestParam("name") String name  // either take country or city i changed in PropertyRepository
    ){
       return propertyRepository.SearchProperty(name);
    }
}
