package com.gocheeta.api.controller;

import com.gocheeta.api.entity.Vehicle;
import com.gocheeta.api.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody Vehicle streetAddress) {
        return new ResponseEntity<Vehicle>(vehicleService.saveAndUpdate(streetAddress), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> update(@PathVariable("id") long id, @RequestBody Vehicle updateDetails) {
        updateDetails.setId(id);
        return new ResponseEntity<Vehicle>(vehicleService.saveAndUpdate(updateDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map> delete(@PathVariable("id") long id) {
        try {
            vehicleService.delete(id);
            Map response = new HashMap();
            response.put("status", HttpStatus.OK);
            response.put("message", "Successfully Deleted");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch(Exception exception) {
            throw exception;
        }
    }

    @GetMapping()
    public List<Vehicle> getAll() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/byBranch/{id}")
    public List<Vehicle> getByBranch(@PathVariable("id") long id) {
        return vehicleService.getAllVehiclesByBranchId(id);
    }

    @GetMapping("/byCategory/{id}")
    public List<Vehicle> getByCategory(@PathVariable("id") long id) {
        return vehicleService.getAllVehicleByCategoryId(id);
    }
}
