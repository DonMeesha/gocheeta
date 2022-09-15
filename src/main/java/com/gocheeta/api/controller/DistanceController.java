package com.gocheeta.api.controller;

import com.gocheeta.api.entity.Distance;
import com.gocheeta.api.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/distance")
public class DistanceController {

    @Autowired
    private DistanceService distanceService;

    @PostMapping
    public ResponseEntity<Distance> create(@RequestBody Distance distance) {
        return new ResponseEntity<Distance>(distanceService.saveAndUpdate(distance), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Distance> update(@RequestBody Distance distance, @PathVariable long id) {
        return new ResponseEntity<Distance>(distanceService.saveAndUpdate(distance), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map> delete(@PathVariable long id) {
        try {
            distanceService.delete(id);
            Map response = new HashMap();
            response.put("status", HttpStatus.OK);
            response.put("message", "Successfully Deleted");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch(Exception exception) {
            throw exception;
        }
    }

    @GetMapping()
    public List<Distance> getAll() {
        return distanceService.getAllDistances();
    }

    @GetMapping("/price")
    public ResponseEntity<Map<String, Double>> getTotalPrice(@RequestParam("start") long startCity, @RequestParam("end") long endCity, @RequestParam("category") long category) {
        Map<String, Double> total = new HashMap<String, Double>();
        Double totalPrice = distanceService.totalPriceCalculator(startCity, endCity, category);
        total.put("totalPrice", totalPrice);
        return new ResponseEntity<Map<String, Double>>(total, HttpStatus.OK);
    }
}
