package com.foodwagon.backend.repository;

import com.foodwagon.backend.entity.FreshStoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreshStoreCategoryRepository extends JpaRepository<FreshStoreCategory, Long> {
}