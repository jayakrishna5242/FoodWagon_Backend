package com.foodwagon.backend.controller;

import com.foodwagon.backend.dto.geniebooking.GenieBookingUpdateRequest;
import com.foodwagon.backend.entity.GenieBooking;
import com.foodwagon.backend.enums.BookingStatus;
import com.foodwagon.backend.service.GenieBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genie")
public class GenieBookingController {

    @Autowired
    private GenieBookingService genieBookingService;

    @PostMapping
    public GenieBooking createBooking(@RequestBody GenieBooking booking) {
        return genieBookingService.createBooking(booking);
    }

    @GetMapping("/user/{userId}")
    public List<GenieBooking> getBookingsByUserId(@PathVariable Long userId) {
        return genieBookingService.getBookingsByUserId(userId);
    }

    @GetMapping("/rider/{riderId}")
    public List<GenieBooking> getBookingsByRiderId(@PathVariable Long riderId) {
        return genieBookingService.getBookingsByRiderId(riderId);
    }

    @GetMapping
    public List<GenieBooking> getAllBookings() {
        return genieBookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public GenieBooking getBookingById(@PathVariable Long id) {
        return genieBookingService.getBookingById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @PatchMapping("/{id}/status")
    public GenieBooking updateStatus(@PathVariable Long id ,@RequestBody GenieBookingUpdateRequest request) {
        return genieBookingService.updateStatus(id, request.getStatus(), request.getRiderId());
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        genieBookingService.deleteBooking(id);
    }

    @PatchMapping("/{id}/accept")
    public GenieBooking acceptBooking(@PathVariable Long id ,@RequestBody GenieBookingUpdateRequest request){
        return genieBookingService.updateStatus(id, BookingStatus.ASSIGNED,request.getRiderId());
    }
}
