package com.gocheeta.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "streetaddress")
@Data
public class StreetAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_details_id", nullable = false)
    private Branch branchDetails;

    @OneToMany(mappedBy = "streetAddressDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore()
    private Set<Booking> bookingDetails;
}
