package com.gocheeta.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column()
    private String contactNumber;

    @Column(nullable = false)
    private String password;

    @Column()
    private String imageUrl;

    @OneToMany(mappedBy = "customerDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore()
    private Set<Booking> bookingDetails;
}
