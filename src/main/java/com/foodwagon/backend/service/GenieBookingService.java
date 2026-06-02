package com.foodwagon.backend.service;

import com.foodwagon.backend.entity.GenieBooking;
import com.foodwagon.backend.enums.BookingStatus;
import com.foodwagon.backend.repository.GenieBookingRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class GenieBookingService {

    @Autowired
    private  GenieBookingRepository genieBookingRepository;

    public GenieBooking createBooking(GenieBooking booking) {
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());
        booking.setStatus(BookingStatus.PENDING);
        return genieBookingRepository.save(booking);
    }

    public List<GenieBooking> getBookingsByUserId(Long userId) {
        return genieBookingRepository.findByUserId(userId);
    }

    public List<GenieBooking> getAllBookings() {
        return genieBookingRepository.findAll();
    }

    public Optional<GenieBooking> getBookingById(Long id) {
        return genieBookingRepository.findById(id);
    }

    public GenieBooking updateStatus(Long id, BookingStatus status, Long riderId) {
        GenieBooking booking = genieBookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if(riderId!=null){
            booking.setRiderId(riderId);
        }
        booking.setStatus(status);
        booking.setUpdatedAt(LocalDateTime.now());

        return genieBookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        genieBookingRepository.deleteById(id);
    }

    public List<GenieBooking> getBookingsByRiderId(Long riderId){
        return genieBookingRepository.findByRiderId(riderId);
    }
}
