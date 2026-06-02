package com.foodwagon.backend.dto.restaurant;

import lombok.Data;
import java.util.List;

@Data
public class FreshStoreRequest {
    private String name;
    private String image;
    private String category;
    private Double rating;
    private String time;
    private String distance;
    private List<String> items;
}