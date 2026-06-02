package com.foodwagon.backend.service;

import com.foodwagon.backend.dto.restaurant.FreshStoreCategoryDTO;
import com.foodwagon.backend.entity.FreshStoreCategory;
import com.foodwagon.backend.repository.FreshStoreCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FreshStoreCategoryService {

    @Autowired
    private FreshStoreCategoryRepository categoryRepository;

    public FreshStoreCategory createCategory(FreshStoreCategoryDTO dto) {
        FreshStoreCategory category = new FreshStoreCategory();
        category.setName(dto.getName());
        category.setImage(dto.getImage());

        return categoryRepository.save(category);
    }

    public List<FreshStoreCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    public FreshStoreCategory getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}