package com.gocheeta.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @OneToMany(mappedBy = "categoryDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Vehicle> vehicleDetails;
}
