package com.gocheeta.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "branch")
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "branchDetails", cascade = CascadeType.ALL)
    @JsonIgnore()
    private Set<StreetAddress> address;

    @OneToMany(mappedBy = "branchDetails", cascade = CascadeType.ALL)
    @JsonIgnore()
    private Set<Vehicle> vehicleDetails;

    @OneToMany(mappedBy = "branchDetails", cascade = CascadeType.ALL)
    @JsonIgnore()
    private Set<Driver> driverDetails;
}
