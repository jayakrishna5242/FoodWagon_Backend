package com.foodwagon.backend.repository;

import com.foodwagon.backend.entity.SupermarketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SupermarketItemRepository extends JpaRepository<SupermarketItem, Long> {

    List<SupermarketItem> findByCategory(String category);

    List<SupermarketItem> findByNameContainingIgnoreCase(String name);

    List<SupermarketItem> findByCategoryAndNameContainingIgnoreCase(String category, String name);

    @Query("SELECT DISTINCT s.category FROM SupermarketItem s")
    Set<String> findAllCategories();
}