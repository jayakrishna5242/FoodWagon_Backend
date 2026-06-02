package com.foodwagon.backend.repository;

import com.foodwagon.backend.entity.FreshStoreItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FreshStoreItemRepository extends JpaRepository<FreshStoreItem, Long> {
    List<FreshStoreItem> findByStoreId(Long storeId);
    List<FreshStoreItem> findByCategory(String category);
    List<FreshStoreItem> findByStoreIdAndCategory(Long storeId, String category);
    void deleteByStoreId(Long storeId);
}