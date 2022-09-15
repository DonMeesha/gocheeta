package com.gocheeta.api.service.impl;

import com.gocheeta.api.entity.Category;
import com.gocheeta.api.repository.CategoryRepository;
import com.gocheeta.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICategoryService implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category saveAndUpdate(Category newCategory) {
        try {
            return repository.save(newCategory);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public Category findById(Long id) {
        try {
            return repository.findById(id).get();
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
    public List<Category> getAllCategories() {
        try {
            return repository.findAll();
        } catch (Exception exception) {
            throw exception;
        }
    }
}
