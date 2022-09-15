package com.gocheeta.api.repository;

import com.gocheeta.api.entity.Customer;
import com.gocheeta.api.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findByEmail(String customerEmail);
}
