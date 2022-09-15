package com.gocheeta.api.service.impl;

import com.gocheeta.api.entity.StreetAddress;
import com.gocheeta.api.repository.StreetAddressRepository;
import com.gocheeta.api.service.StreetAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IStreetAddressService implements StreetAddressService {

    @Autowired
    private StreetAddressRepository repository;

    @Override
    public StreetAddress saveAndUpdate(StreetAddress newAddress) {
        try {
            return repository.save(newAddress);
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
    public List<StreetAddress> getAllAddresses() {
        try {
            return repository.findAll();
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public List<StreetAddress> getAllAddressesForGivenBranchId(Long id) {
        try {
            return repository.getStreetAddressForGivenBranchId(id);
        } catch (Exception exception) {
            throw exception;
        }
    }
}
