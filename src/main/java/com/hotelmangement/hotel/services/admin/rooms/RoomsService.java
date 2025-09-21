package com.hotelmangement.hotel.services.admin.rooms;

import com.hotelmangement.hotel.dto.RoomDto;
import com.hotelmangement.hotel.dto.RoomResponseDto;

public interface RoomsService {
    
    
    boolean postRoom(RoomDto roomDto);

    RoomResponseDto getAllRoom(int pageNumber);

    RoomDto getRoomById(Long id);

    boolean updateRoom(Long id, RoomDto roomDto);

    void deleteRoom(Long id) ;
}
