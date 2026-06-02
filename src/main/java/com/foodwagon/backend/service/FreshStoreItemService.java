package com.foodwagon.backend.service;

import com.foodwagon.backend.dto.restaurant.FreshStoreItemRequest;
import com.foodwagon.backend.entity.FreshStoreItem;
import com.foodwagon.backend.repository.FreshStoreItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FreshStoreItemService {

    @Autowired
    private FreshStoreItemRepository freshStoreItemRepository;

    public FreshStoreItem createItem(FreshStoreItemRequest request) {
        FreshStoreItem item = new FreshStoreItem();
        item.setStoreId(request.getStoreId());
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        item.setImageUrl(request.getImageUrl());
        item.setDescription(request.getDescription());
        item.setWeight(request.getWeight());
        item.setCategory(request.getCategory());

        return freshStoreItemRepository.save(item);
    }

    public List<FreshStoreItem> getAllItems() {
        return freshStoreItemRepository.findAll();
    }

    public FreshStoreItem getItemById(Long id) {
        return freshStoreItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
    }

    public List<FreshStoreItem> getItemsByStoreId(Long storeId) {
        return freshStoreItemRepository.findByStoreId(storeId);
    }

    public List<FreshStoreItem> getItemsByCategory(String category) {
        return freshStoreItemRepository.findByCategory(category);
    }

    public FreshStoreItem updateItem(Long id, FreshStoreItemRequest request) {
        FreshStoreItem item = getItemById(id);
        item.setStoreId(request.getStoreId());
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        item.setImageUrl(request.getImageUrl());
        item.setDescription(request.getDescription());
        item.setWeight(request.getWeight());
        item.setCategory(request.getCategory());

        return freshStoreItemRepository.save(item);
    }

    public void deleteItem(Long id) {
        freshStoreItemRepository.deleteById(id);
    }

    public void deleteItemsByStoreId(Long storeId) {
        freshStoreItemRepository.deleteByStoreId(storeId);
    }
}