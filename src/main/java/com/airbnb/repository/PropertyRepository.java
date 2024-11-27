package com.airbnb.repository;

import com.airbnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    //1st method
//    @Query("select p from Property p join City c on p.city =c.id  where  c.name = :CityName")
//    List<Property> SearchProperty(
//        @Param("CityName") String CityName
//    );


    // if not used very well query then project will not run and crashed
    //2d method is mainly used in by devloper and remember
//    @Query("select p from Property p JOIN p.city c  where  c.name = :CityName")
//    List<Property> SearchProperty(
//            @Param("CityName") String CityName
//    );

//    add multiple join
    @Query("select p from Property p JOIN p.city c JOIN p.country co  where  c.name =:name or co.name=:name")
    List<Property> SearchProperty(
            @Param("name") String name
    );

}