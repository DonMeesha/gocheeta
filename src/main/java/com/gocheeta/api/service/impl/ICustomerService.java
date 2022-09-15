package com.gocheeta.api.service.impl;

import com.gocheeta.api.entity.Customer;
import com.gocheeta.api.repository.CustomerRepository;
import com.gocheeta.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICustomerService implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Customer saveAndUpdate(Customer newCustomer) {
        try {
            if (!newCustomer.getPassword().equals(null)) {
                newCustomer.setPassword(passwordEncoder.encode(newCustomer.getPassword()));
            }
            return repository.save(newCustomer);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        try {
            return repository.findAll();
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public Customer getUserByEmail(String email) {
        try {
            return repository.findByEmail(email);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public Customer getUserByContactNumber(String contactNumber) {
        try {
            return repository.findByContactNumber(contactNumber);
        } catch (Exception exception) {
            throw exception;
        }
    }
}
