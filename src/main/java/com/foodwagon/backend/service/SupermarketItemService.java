package com.foodwagon.backend.service;

import com.foodwagon.backend.entity.SupermarketItem;
import com.foodwagon.backend.repository.SupermarketItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SupermarketItemService {

    @Autowired
    private SupermarketItemRepository repository;

    public List<SupermarketItem> getItems(String category, String query) {
        if (category != null && query != null && !query.isEmpty()) {
            return repository.findByCategoryAndNameContainingIgnoreCase(category, query);
        } else if (category != null) {
            return repository.findByCategory(category);
        } else if (query != null && !query.isEmpty()) {
            return repository.findByNameContainingIgnoreCase(query);
        }
        return repository.findAll();
    }

    public Set<String> getAllCategories() {
        return repository.findAllCategories();
    }

    public SupermarketItem saveItem(SupermarketItem item) {
        return repository.save(item);
    }

    public Optional<SupermarketItem> getItemById(Long id) {
        return repository.findById(id);
    }

    public SupermarketItem updateItem(Long id, SupermarketItem details) {
        SupermarketItem item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setName(details.getName());
        item.setCategory(details.getCategory());
        item.setPrice(details.getPrice());
        item.setDiscountPrice(details.getDiscountPrice());
        item.setUnit(details.getUnit());
        item.setDescription(details.getDescription());
        item.setImageUrl(details.getImageUrl());
        item.setIsAvailable(details.getIsAvailable());

        return repository.save(item);
    }

    public void deleteItem(Long id) {
        repository.deleteById(id);
    }
}