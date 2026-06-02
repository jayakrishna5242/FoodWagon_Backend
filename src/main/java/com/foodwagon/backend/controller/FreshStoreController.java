package com.foodwagon.backend.controller;

import com.foodwagon.backend.dto.restaurant.FreshStoreRequest;
import com.foodwagon.backend.entity.FreshStore;
import com.foodwagon.backend.service.FreshStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fresh-stores")

public class FreshStoreController {

    @Autowired
    private FreshStoreService freshStoreService;

    @PostMapping
    public ResponseEntity<FreshStore> createStore(@RequestBody FreshStoreRequest request) {
        FreshStore store = freshStoreService.createStore(request);
        return new ResponseEntity<>(store, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FreshStore>> getAllStores() {
        return ResponseEntity.ok(freshStoreService.getAllStores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FreshStore> getStoreById(@PathVariable Long id) {
        return ResponseEntity.ok(freshStoreService.getStoreById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FreshStore> updateStore(@PathVariable Long id, @RequestBody FreshStoreRequest request) {
        FreshStore store = freshStoreService.updateStore(id, request);
        return ResponseEntity.ok(store);
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<FreshStore>> getTopRatedStores() {
        return ResponseEntity.ok(freshStoreService.getTopRatedStores());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        freshStoreService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }
}