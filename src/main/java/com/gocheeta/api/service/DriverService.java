package com.gocheeta.api.service;

import com.gocheeta.api.entity.Driver;

import java.util.List;

public interface DriverService {
    Driver saveAndUpdate(Driver newDriver);
    void delete(Long id);
    List<Driver> getAllDrivers();
    Driver getDriverByEmail(String email);
}
