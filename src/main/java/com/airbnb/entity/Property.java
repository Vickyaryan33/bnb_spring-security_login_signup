package com.airbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number_of_guest", nullable = false)
    private Integer numberOfGuest;

    @Column(name = "number_of_bed", nullable = false)
    private Integer numberOfBed;

    @Column(name = "number_of_bathroom", nullable = false)
    private Integer numberOfBathroom;

    @Column(name = "number_of_bedroom", nullable = false)
    private Integer numberOfBedroom;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

}