package com.hotelmangement.hotel.services.admin.reservation;

import com.hotelmangement.hotel.dto.ReservationResponseDto;

public interface ReservationService {
    
    ReservationResponseDto getAllReservations(int pageNumber);

    boolean changeReservationStatus(Long id, String status);
}
