package com.foodwagon.backend.dto.geniebooking;

import com.foodwagon.backend.enums.BookingStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Builder
public class GenieBookingUpdateRequest {

         Long id;
         BookingStatus status;
         Long riderId;

}
