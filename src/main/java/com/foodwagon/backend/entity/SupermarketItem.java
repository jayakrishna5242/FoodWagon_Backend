package com.foodwagon.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supermarket_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupermarketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Double price;

    private Double discountPrice;

    @Column(nullable = false)
    private String unit; // e.g., "1 kg", "500g", "1 unit"

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Boolean isAvailable = true;
}