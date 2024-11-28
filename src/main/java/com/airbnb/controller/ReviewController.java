package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Review;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> createReview(
            @RequestBody Review review,
            @AuthenticationPrincipal AppUser appUser,
            @RequestParam("propertyId") Long propertyId
            ) {
        Property property = propertyRepository.findById(propertyId).get();
        //for uniqe review
        Review byUserAndProperty = reviewRepository.findByUserAndProperty(appUser, property);
        if (byUserAndProperty != null) {
            return new ResponseEntity<>("Review already exists", HttpStatus.BAD_REQUEST);
        }
        review.setAppUser(appUser);
        review.setProperty(property);
        Review review1 = reviewRepository.save(review);
        return new  ResponseEntity<>(review1, HttpStatus.CREATED);

    }


    //get all review of that user
    @GetMapping("/reviewList")
    public ResponseEntity<List<Review>> reviewList(
          @AuthenticationPrincipal AppUser appUser
    ){

        List<Review> reviewByUser = reviewRepository.findReviewByUser(appUser);
        return new ResponseEntity<>(reviewByUser, HttpStatus.OK);

    }

}
