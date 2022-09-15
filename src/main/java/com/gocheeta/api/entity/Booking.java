package com.gocheeta.api.entity;

import com.gocheeta.api.entity.enums.BookingStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "booking")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column()
    private double startTimeStamp;

    @Column()
    private double endTimeStamp;

    @Column(nullable = false)
    private BookingStatus status;

    @Column()
    private String clientFeedback;

    @Column()
    private int clientRate;

    @Column()
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_details_id", nullable = false)
    private Customer customerDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "street_details_id", nullable = false)
    private StreetAddress streetAddressDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_details_id", nullable = false)
    private Driver driverDetails;
}
