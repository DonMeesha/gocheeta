package com.gocheeta.api.service;

import com.gocheeta.api.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveAndUpdate(Customer newCustomer);
    void delete(Long id);
    List<Customer> getAllCustomers();
    Customer getUserByEmail(String email);
    Customer getUserByContactNumber(String contactNumber);
}
