package com.gocheeta.api.service;

import com.gocheeta.api.entity.Category;
import com.gocheeta.api.entity.Distance;

import java.util.List;

public interface DistanceService {
    Distance saveAndUpdate(Distance newDistance);
    void delete(Long id);
    List<Distance> getAllDistances();
    double totalPriceCalculator(long startCityId, long endCityId, long category);
}
