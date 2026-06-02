package com.foodwagon.backend.dto.restaurant;

import lombok.Data;

@Data
public class FreshStoreItemRequest {
    private Long storeId;
    private String name;
    private Double price;
    private String imageUrl;
    private String description;
    private String weight;
    private String category;
}
