package com.gocheeta.api.controller;

import com.gocheeta.api.entity.StreetAddress;
import com.gocheeta.api.service.StreetAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/street")
public class StreetAddressController {

    @Autowired
    private StreetAddressService streetAddressService;

    @PostMapping
    public ResponseEntity<StreetAddress> create(@RequestBody StreetAddress streetAddress) {
        return new ResponseEntity<StreetAddress>(streetAddressService.saveAndUpdate(streetAddress), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StreetAddress> update(@PathVariable("id") long id, @RequestBody StreetAddress updateStreetDetails) {
        updateStreetDetails.setId(id);
        return new ResponseEntity<StreetAddress>(streetAddressService.saveAndUpdate(updateStreetDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map> delete(@PathVariable("id") long id) {
        try {
            streetAddressService.delete(id);
            Map response = new HashMap();
            response.put("status", HttpStatus.OK);
            response.put("message", "Successfully Deleted");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch(Exception exception) {
            throw exception;
        }
    }

    @GetMapping()
    public List<StreetAddress> getAll() {

        return streetAddressService.getAllAddresses();
    }

    @GetMapping("/byBranch/{id}")
    public List<StreetAddress> getDetailsByBranchId(@PathVariable("id") long branchId) {
        return streetAddressService.getAllAddressesForGivenBranchId(branchId);
    }
}
