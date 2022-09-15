package com.gocheeta.api.service.impl;

import com.gocheeta.api.entity.Vehicle;
import com.gocheeta.api.repository.VehicleRepository;
import com.gocheeta.api.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IVehicleService implements VehicleService {

    @Autowired
    private VehicleRepository repository;

    @Override
    public Vehicle saveAndUpdate(Vehicle newVehicle) {
        try {
            return repository.save(newVehicle);
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
    public List<Vehicle> getAllVehicles() {
        try {
            return repository.findAll();
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public List<Vehicle> getAllVehiclesByBranchId(Long id) {
        try {
            return repository.getVehicleBelongsToBranch(id);
        } catch(Exception exception) {
            throw exception;
        }
    }

    @Override
    public List<Vehicle> getAllVehicleByCategoryId(Long id) {
        try {
            return repository.getVehicleBelongsToCategory(id);
        } catch(Exception exception) {
            throw exception;
        }
    }
}
