package com.foodwagon.backend.controller;

import com.foodwagon.backend.dto.order.*;
import com.foodwagon.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final SimpMessagingTemplate messagingTemplate;

    /* PLACE ORDER */
    @PostMapping
    public OrderResponse placeOrder(@RequestBody CreateOrderRequest request) {
        OrderResponse savedOrder = orderService.placeOrder(request);

        // 🔔 Notify restaurant dashboard — matches frontend: /topic/restaurant.{id}
        messagingTemplate.convertAndSend(
                "/topic/restaurant." + savedOrder.restaurantId(),
                savedOrder
        );

        return savedOrder;
    }

    /* USER ORDER HISTORY */
    @GetMapping("/user/{userId}")
    public List<CustomerOrderHistoryResponse> getUserOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }

    /* RESTAURANT ORDER QUEUE */
    @GetMapping("/restaurant/{restaurantId}")
    public List<RestaurantOrderQueueResponse> getRestaurantOrders(@PathVariable Long restaurantId) {
        return orderService.getRestaurantOrders(restaurantId);
    }

    /* UPDATE STATUS */
    @PatchMapping("/{orderId}/status")
    public OrderStatusUpdateRequest updateStatus(
            @PathVariable Long orderId,
            @RequestBody OrderStatusUpdateRequest request
    ) {
        OrderStatusUpdateRequest updated = orderService.updateStatus(orderId, request);

        // 🔔 Notify specific customer — matches frontend: /user/queue/order-status
        messagingTemplate.convertAndSend(
                "/topic/user." + updated.userId() + ".order-status",
                updated
        );

        return updated;
    }
}