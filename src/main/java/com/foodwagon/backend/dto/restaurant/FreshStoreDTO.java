package com.foodwagon.backend.dto.restaurant;

import lombok.Data;
import java.util.List;

@Data
public class FreshStoreDTO {
    private Long id;
    private String name;
    private String image;
    private Double rating;
    private String time;
    private String distance;
    private List<String> items;
}