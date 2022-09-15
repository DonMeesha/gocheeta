package com.gocheeta.api.service.impl;

import com.gocheeta.api.entity.Category;
import com.gocheeta.api.entity.Distance;
import com.gocheeta.api.repository.DistanceRepository;
import com.gocheeta.api.service.CategoryService;
import com.gocheeta.api.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class IDistanceService implements DistanceService {
    @Autowired
    private DistanceRepository repository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public Distance saveAndUpdate(Distance newDistance) {
        try {
            return repository.save(newDistance);
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
    public List<Distance> getAllDistances() {
        try {
            return repository.findAll();
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public double totalPriceCalculator(long startCityId, long endCityId, long categoryId) {
        try {
            List<Distance> availableCities = repository.findByStartCityId(startCityId);
            Category categoryInfo = categoryService.findById(categoryId);
            AtomicReference<Distance> selectedDistance = null;
            availableCities.forEach(selectedCity -> {
                if(selectedCity.getEndCityId() == endCityId) {
                    selectedDistance.set(selectedCity);
                }
            });
            return selectedDistance.get().getTotalDistance() * categoryInfo.getPrice();
        } catch (Exception exception) {
            throw exception;
        }
    }
}
