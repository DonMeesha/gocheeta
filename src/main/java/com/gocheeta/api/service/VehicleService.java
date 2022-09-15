package com.gocheeta.api.service;

import com.gocheeta.api.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle saveAndUpdate(Vehicle newVehicle);
    void delete(Long id);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAllVehiclesByBranchId(Long id);
    List<Vehicle> getAllVehicleByCategoryId(Long id);
}
