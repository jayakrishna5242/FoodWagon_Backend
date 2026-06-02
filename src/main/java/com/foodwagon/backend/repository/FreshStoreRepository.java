package com.foodwagon.backend.repository;

import com.foodwagon.backend.entity.FreshStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FreshStoreRepository extends JpaRepository<FreshStore, Long> {

    // Basic finders
    List<FreshStore> findByRatingGreaterThanEqual(Double rating);

    List<FreshStore> findByRatingBetween(Double start, Double end);

    List<FreshStore> findByDistanceLessThanEqual(String distance);

    // Sorting queries - Correct syntax
    List<FreshStore> findAllByOrderByRatingDesc();

    List<FreshStore> findAllByOrderByRatingDescTimeAsc();

    List<FreshStore> findAllByOrderByTimeAsc();

    // Combined conditions with sorting
    List<FreshStore> findByRatingGreaterThanEqualOrderByRatingDesc(Double rating);

    List<FreshStore> findByRatingGreaterThanEqualOrderByRatingDescTimeAsc(Double rating);

    // Custom queries
    @Query("SELECT s FROM FreshStore s ORDER BY s.rating DESC")
    List<FreshStore> getTopRatedStores();

    @Query("SELECT s FROM FreshStore s ORDER BY s.rating DESC, s.time ASC")
    List<FreshStore> getTopRatedStoresOrderByTime();

    @Query("SELECT s FROM FreshStore s WHERE s.rating >= :minRating ORDER BY s.rating DESC, s.time ASC")
    List<FreshStore> getStoresWithMinRating(@Param("minRating") Double minRating);

    // Native query example
    @Query(value = "SELECT * FROM fresh_stores ORDER BY rating DESC, time ASC LIMIT :limit", nativeQuery = true)
    List<FreshStore> getTopNStores(@Param("limit") int limit);
}