package com.foodwagon.backend.repository;

import com.foodwagon.backend.entity.GenieBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenieBookingRepository extends JpaRepository<GenieBooking,Long> {

    public List<GenieBooking> findByUserId(Long userId);
    public List<GenieBooking> findByRiderId(Long riderId);

}
