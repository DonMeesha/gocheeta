package com.gocheeta.api.controller;

import com.gocheeta.api.entity.Customer;
import com.gocheeta.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> save(@RequestBody Customer customerDetails) {
        return new ResponseEntity<Customer>(customerService.saveAndUpdate(customerDetails), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@RequestBody Customer newCustomerDetails, @PathVariable("id") long id) {
        newCustomerDetails.setId(id);
        return new ResponseEntity<Customer>(customerService.saveAndUpdate(newCustomerDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map> delete(@PathVariable("id") long id) {
        try {
            customerService.delete(id);
            Map response = new HashMap();
            response.put("status", HttpStatus.OK);
            response.put("message", "Successfully Deleted");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @GetMapping()
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

}
