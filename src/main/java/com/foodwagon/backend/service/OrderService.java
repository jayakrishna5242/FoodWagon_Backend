package com.foodwagon.backend.service;

import com.foodwagon.backend.dto.order.*;
import com.foodwagon.backend.entity.Order;
import com.foodwagon.backend.entity.OrderItem;
import com.foodwagon.backend.entity.Restaurant;
import com.foodwagon.backend.entity.User;
import com.foodwagon.backend.enums.OrderStatus;
import com.foodwagon.backend.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j  // ADD THIS for logging
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    /* ---------------- PLACE ORDER ---------------- */
    public OrderResponse placeOrder(CreateOrderRequest request) {
        // Fetch restaurant
        Restaurant restaurant = restaurantRepository
                .findById(request.restaurantId())
                .orElse(null);

        String restaurantName = restaurant != null ? restaurant.getName() : null;

        // Fetch customer to get their name
        String customerName = "Customer"; // Default fallback
        try {
            User customer = userRepository.findById(request.userId()).orElse(null);
            if (customer != null) {
                customerName = customer.getName();
            }
        } catch (Exception e) {
            log.error("Failed to fetch customer name for userId: {}", request.userId(), e);
        }

        Order order = Order.builder()
                .userId(request.userId())
                .restaurantId(request.restaurantId())
                .restaurantName(restaurantName)
                .totalAmount(request.totalAmount())
                .deliveryAddress(request.deliveryAddress())
                .paymentMethod(request.paymentMethod())
                .status(OrderStatus.PENDING)
                .createdAt(Instant.now())
                .build();

        List<OrderItem> items = request.items().stream()
                .map(i -> OrderItem.builder()
                        .menuItemId(i.id())
                        .name(i.name())
                        .price(i.price())
                        .quantity(i.quantity())
                        .order(order)
                        .build())
                .toList();

        order.setItems(items);
        Order saved = orderRepository.save(order);

        return mapToOrderResponse(saved, customerName); // Pass customer name to mapper
    }

    /* ---------------- USER ORDERS ---------------- */
    public List<CustomerOrderHistoryResponse> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::mapToCustomerHistory)
                .toList();
    }

    private CustomerOrderHistoryResponse mapToCustomerHistory(Order order) {
        // Fetch restaurant for this order
        Restaurant restaurant = restaurantRepository
                .findById(order.getRestaurantId())
                .orElse(null);

        String imageUrl = restaurant != null ? restaurant.getImageUrl() : null;

        return new CustomerOrderHistoryResponse(
                order.getId(),
                order.getRestaurantName(),
                imageUrl,
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt(),
                mapItems(order)
        );
    }

    /* ---------------- RESTAURANT QUEUE ---------------- */
    public List<RestaurantOrderQueueResponse> getRestaurantOrders(Long restaurantId) {
        return orderRepository
                .findByRestaurantIdAndStatusNot(restaurantId, OrderStatus.DELIVERED)
                .stream()
                .map(this::mapToRestaurantQueue)
                .toList();
    }

    /* UPDATE STATUS */
    public OrderStatusUpdateRequest updateStatus(Long orderId, OrderStatusUpdateRequest request) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(request.status());
        orderRepository.save(order);

        // Include orderId and userId in response
        return new OrderStatusUpdateRequest(
                order.getId(),      // orderId
                order.getUserId(),  // userId
                request.status()    // status
        );
    }

    /* ---------------- MAPPERS ---------------- */

    // UPDATED: Now accepts customerName parameter
    private OrderResponse mapToOrderResponse(Order order, String customerName) {
        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                customerName,  // ADDED: customer name
                order.getRestaurantId(),
                order.getRestaurantName(),
                mapItems(order),
                order.getTotalAmount(),
                order.getStatus(),  // Convert enum to string
                order.getCreatedAt(),
                order.getDeliveryAddress()
        );
    }

    // Keep the original method for backward compatibility if needed
    private OrderResponse mapToOrderResponse(Order order) {
        // Fetch customer name
        String customerName = "Customer";
        try {
            User customer = userRepository.findById(order.getUserId()).orElse(null);
            if (customer != null) {
                customerName = customer.getName();
            }
        } catch (Exception e) {
            log.error("Failed to fetch customer name for userId: {}", order.getUserId(), e);
        }

        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                customerName,  // ADDED: customer name
                order.getRestaurantId(),
                order.getRestaurantName(),
                mapItems(order),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getDeliveryAddress()
        );
    }

    private RestaurantOrderQueueResponse mapToRestaurantQueue(Order order) {
        User user = userRepository.findById(order.getUserId()).orElse(null);

        String userPhone = (user != null && user.getPhone() != null)
                ? user.getPhone().toString()
                : null;
        String userName = (user != null) ? user.getName() : null;

        return new RestaurantOrderQueueResponse(
                order.getId(),
                userName,
                userPhone,
                mapItems(order),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }

    private List<OrderItemDTO> mapItems(Order order) {
        return order.getItems().stream()
                .map(i -> new OrderItemDTO(
                        i.getMenuItemId(),
                        i.getName(),
                        i.getQuantity(),
                        i.getPrice()
                ))
                .toList();
    }
}