package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Review;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewController(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    @RequestMapping("/createReview")
    public ResponseEntity<Review> createReview(
            @RequestBody Review review,
            @AuthenticationPrincipal AppUser appUser,
            @RequestParam("propertyId") Long propertyId
            ) {
        Property property = propertyRepository.findById(propertyId).get();
        review.setAppUser(appUser);
        review.setProperty(property);
        Review review1 = reviewRepository.save(review);
        return new  ResponseEntity<>(review1, HttpStatus.CREATED);

    }

}
