package com.foodwagon.backend.controller;

import com.foodwagon.backend.entity.SupermarketItem;
import com.foodwagon.backend.service.SupermarketItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/supermarket")

public class SupermarketItemController {

    @Autowired
    private SupermarketItemService supermarketItemService;

    // GET /api/supermarket/items?category=Fruits&query=apple
    @GetMapping("/items")
    public List<SupermarketItem> getItems(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String query) {
        return supermarketItemService.getItems(category, query);
    }

    // GET /api/supermarket/categories
    @GetMapping("/categories")
    public Set<String> getCategories() {
        return supermarketItemService.getAllCategories();
    }

    @PostMapping("/items")
    public SupermarketItem createItem(@RequestBody SupermarketItem item) {
        return supermarketItemService.saveItem(item);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<SupermarketItem> getItemById(@PathVariable Long id) {
        return supermarketItemService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<SupermarketItem> updateItem(@PathVariable Long id, @RequestBody SupermarketItem itemDetails) {
        return ResponseEntity.ok(supermarketItemService.updateItem(id, itemDetails));
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        supermarketItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}