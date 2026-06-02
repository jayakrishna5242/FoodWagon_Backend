package com.foodwagon.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fresh_stores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreshStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private String distance;

    @ElementCollection
    @CollectionTable(name = "fresh_store_items_list", joinColumns = @JoinColumn(name = "store_id"))
    @Column(name = "item_name")
    private List<String> items = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FreshStoreItem> storeItems = new ArrayList<>();
}