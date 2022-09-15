package com.gocheeta.api.controller;

import com.gocheeta.api.entity.Category;
import com.gocheeta.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<Category> insert(@RequestBody Category category) {
        return new ResponseEntity<Category>(categoryService.saveAndUpdate(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") long id, @RequestBody Category category) {
        category.setId(id);
        return new ResponseEntity<Category>(categoryService.saveAndUpdate(category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map> delete(@PathVariable("id") long id) {
        try {
            categoryService.delete(id);
            Map response = new HashMap();
            response.put("status", HttpStatus.OK);
            response.put("message", "Successfully Deleted");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @GetMapping()
    public List<Category> getAll() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") long id) {
        return new ResponseEntity<Category>(categoryService.findById(id), HttpStatus.OK);
    }
}
