package com.hotelmangement.hotel.services.customer.room;

import com.hotelmangement.hotel.dto.RoomResponseDto;

public interface RoomService {
    
    RoomResponseDto getAvialableRooms(int pageNumber);

}
