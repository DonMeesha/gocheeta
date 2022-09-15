package com.gocheeta.api.service.impl;

import com.gocheeta.api.entity.Driver;
import com.gocheeta.api.repository.DriverRepository;
import com.gocheeta.api.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDriverService implements DriverService {

    @Autowired
    private DriverRepository repository;

    private PasswordEncoder passwordEncoder;

    @Override
    public Driver saveAndUpdate(Driver newDriver) {
        try {
            if (!newDriver.getPassword().equals(null)) {
                newDriver.setPassword(passwordEncoder.encode(newDriver.getPassword()));
            }
            return repository.save(newDriver);
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
    public List<Driver> getAllDrivers() {
        try {
            return repository.findAll();
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public Driver getDriverByEmail(String email) {
        try {
            return repository.findByEmail(email);
        } catch(Exception exception) {
            throw exception;
        }
    }
}
