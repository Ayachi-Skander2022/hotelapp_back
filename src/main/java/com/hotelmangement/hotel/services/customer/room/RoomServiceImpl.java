package com.hotelmangement.hotel.services.customer.room;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotelmangement.hotel.dto.RoomResponseDto;
import com.hotelmangement.hotel.entities.Room;
import com.hotelmangement.hotel.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
    
    private final RoomRepository roomRepository;


     public  RoomResponseDto getAvialableRooms(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 6);
       Page<Room> roomPage = roomRepository.findByAvailable(true, pageable);

       RoomResponseDto roomResponseDto = new RoomResponseDto();
       roomResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
       roomResponseDto.setTotalPages(roomPage.getTotalPages());
       roomResponseDto.setRoomDtoList(roomPage.stream().map(Room::geRoomDto).collect(Collectors.toList()));

       return  roomResponseDto;

    }

}
