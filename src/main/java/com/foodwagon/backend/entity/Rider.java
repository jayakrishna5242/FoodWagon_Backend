package com.foodwagon.backend.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "riders")
@Data
@Builder
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

    private String vehicleType; // Bike, Scooter, Cycle
    private String vehicleNumber;
    private String drivingLicense;

    private boolean isVerified = false;
    private boolean isAvailable = true;

    private Double rating = 0.0;
    private Integer ratingCount = 0;

    private Double currentLatitude;
    private Double currentLongitude;
}