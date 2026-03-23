package com.foodwagon.backend.dto.order;

import com.foodwagon.backend.enums.OrderStatus;

public record OrderStatusUpdateRequest(
        Long orderId,
        Long userId,
        OrderStatus status
) {
    // ✅ Allow partial construction from frontend (only status sent)
    public OrderStatusUpdateRequest(OrderStatus status) {
        this(null, null, status);
    }
}