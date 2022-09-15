package com.gocheeta.api.repository;

import com.gocheeta.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByContactNumber(String contactNumber);
    Customer findByEmail(String customerEmail);
}
