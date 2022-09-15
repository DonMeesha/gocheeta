package com.gocheeta.api.controller;

import com.gocheeta.api.entity.Driver;
import com.gocheeta.api.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping()
    public ResponseEntity<Driver> create(@RequestBody Driver newDriver) {
        return new ResponseEntity<Driver>(driverService.saveAndUpdate(newDriver), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> update(@PathVariable("id") long id, @RequestBody Driver newDriverDetails) {
        newDriverDetails.setId(id);
        return new ResponseEntity<Driver>(driverService.saveAndUpdate(newDriverDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map> delete(@PathVariable("id") long id) {
        try {
            driverService.delete(id);
            Map response = new HashMap();
            response.put("status", HttpStatus.OK);
            response.put("message", "Successfully Deleted");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch(Exception exception) {
            throw exception;
        }
    }

    @GetMapping()
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

}
