package com.foodwagon.backend.controller;

import com.foodwagon.backend.dto.menu.MenuItemRequest;
import com.foodwagon.backend.entity.MenuItem;
import com.foodwagon.backend.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService service;

    /* ADD ITEM */
    @PostMapping
    public MenuItem add(@RequestBody MenuItemRequest request) {
        return service.add(request);
    }

    /* TOGGLE STOCK */
    @PatchMapping("/{id}/toggle-stock")
    public MenuItem toggleStock(@PathVariable Long id) {
        MenuItem updatedItem = service.toggleStock(id);
        return updatedItem;
    }

    /* UPDATE ITEM */
    @PutMapping("/{id}")
    public MenuItem update(
            @PathVariable Long id,
            @RequestBody MenuItemRequest request
    ) {
        return service.update(id, request);
    }

    // ✅ Payload matching frontend { itemId, inStock }
    record StockUpdatePayload(String itemId, boolean inStock) {}
}