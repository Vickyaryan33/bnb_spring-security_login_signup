package com.airbnb.repository;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.appUser =:user and r.property =:property")
    Review findByUserAndProperty(
            @Param("user") AppUser user,
            @Param("property") Property property);


    //for all review of this user
    @Query("select r from Review r where r.appUser =:user")
    List<Review> findReviewByUser(
            @Param("user") AppUser user
    );

}

