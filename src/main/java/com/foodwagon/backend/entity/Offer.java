package com.foodwagon.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "offers")
@Data
public class Offer {
    @Id
    private String code; // Usually the code is the ID/Unique
    private String description;


    private String discountType; // PERCENTAGE, FIXED

    private Double discountValue;
    private Double minOrderValue;
    private Long restaurantId; // Null for global offers
}
