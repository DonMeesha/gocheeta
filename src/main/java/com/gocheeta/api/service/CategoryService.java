package com.gocheeta.api.service;

import com.gocheeta.api.entity.Category;

import java.util.List;

public interface CategoryService {
    Category saveAndUpdate(Category newCategory);
    Category findById(Long id);
    void delete(Long id);
    List<Category> getAllCategories();
}
