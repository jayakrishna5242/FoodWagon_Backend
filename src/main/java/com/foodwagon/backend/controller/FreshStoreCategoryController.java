package com.foodwagon.backend.controller;

import com.foodwagon.backend.dto.restaurant.FreshStoreCategoryDTO;
import com.foodwagon.backend.entity.FreshStoreCategory;
import com.foodwagon.backend.service.FreshStoreCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fresh-store-categories")

public class FreshStoreCategoryController {

    @Autowired
    private FreshStoreCategoryService categoryService;

    @PostMapping
    public ResponseEntity<FreshStoreCategory> createCategory(@RequestBody FreshStoreCategoryDTO dto) {
        FreshStoreCategory category = categoryService.createCategory(dto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FreshStoreCategory>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FreshStoreCategory> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}