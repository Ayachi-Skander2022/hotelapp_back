package com.hotelmangement.hotel.services.customer.booking;

import com.hotelmangement.hotel.dto.ReservationDto;
import com.hotelmangement.hotel.dto.ReservationResponseDto;

public interface BookingService {
    
     boolean postReservation(ReservationDto reservationDto);

      ReservationResponseDto getAllReservationByUserId(Long userId, int pageNumber);
}
