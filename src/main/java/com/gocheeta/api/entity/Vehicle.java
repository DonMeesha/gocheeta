package com.gocheeta.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vehicle")
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String vehicleNumber;

    @Column()
    private String imagePath;

    @Column()
    private String brand;

    @Column()
    private int year;

    @Column()
    private int capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_details_id", nullable = false)
    private Category categoryDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_details_id", nullable = false)
    private Branch branchDetails;

    @ManyToMany(mappedBy = "vehicleList", fetch = FetchType.LAZY)
    @JsonIgnore()
    private Set<Driver> driverList;
}
