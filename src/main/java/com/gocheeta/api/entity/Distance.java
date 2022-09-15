package com.gocheeta.api.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "distance")
@Data
public class Distance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long startCityId;

    @Column(nullable = false)
    private long endCityId;

    @Column(nullable = false)
    private double totalDistance;
}
