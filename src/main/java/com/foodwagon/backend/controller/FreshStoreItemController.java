package com.foodwagon.backend.controller;

import com.foodwagon.backend.dto.restaurant.FreshStoreItemRequest;
import com.foodwagon.backend.entity.FreshStoreItem;
import com.foodwagon.backend.service.FreshStoreItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fresh-store-items")

public class FreshStoreItemController {

    @Autowired
    private FreshStoreItemService freshStoreItemService;

    @PostMapping
    public ResponseEntity<FreshStoreItem> createItem(@RequestBody FreshStoreItemRequest request) {
        FreshStoreItem item = freshStoreItemService.createItem(request);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FreshStoreItem>> getAllItems() {
        return ResponseEntity.ok(freshStoreItemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FreshStoreItem> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(freshStoreItemService.getItemById(id));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<FreshStoreItem>> getItemsByStoreId(@PathVariable Long storeId) {
        return ResponseEntity.ok(freshStoreItemService.getItemsByStoreId(storeId));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<FreshStoreItem>> getItemsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(freshStoreItemService.getItemsByCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FreshStoreItem> updateItem(@PathVariable Long id, @RequestBody FreshStoreItemRequest request) {
        FreshStoreItem item = freshStoreItemService.updateItem(id, request);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        freshStoreItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/store/{storeId}")
    public ResponseEntity<Void> deleteItemsByStoreId(@PathVariable Long storeId) {
        freshStoreItemService.deleteItemsByStoreId(storeId);
        return ResponseEntity.noContent().build();
    }
}