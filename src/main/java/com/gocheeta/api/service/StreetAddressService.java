package com.gocheeta.api.service;

import com.gocheeta.api.entity.StreetAddress;

import java.util.List;

public interface StreetAddressService {
    StreetAddress saveAndUpdate(StreetAddress newAddress);
    void delete(Long id);
    List<StreetAddress> getAllAddresses();
    List<StreetAddress> getAllAddressesForGivenBranchId(Long id);
}
