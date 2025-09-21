package com.hotelmangement.hotel.services.admin.rooms;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotelmangement.hotel.dto.RoomDto;
import com.hotelmangement.hotel.dto.RoomResponseDto;
import com.hotelmangement.hotel.entities.Room;
import com.hotelmangement.hotel.repository.RoomRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService{
    
    private final RoomRepository roomRepository;

    public boolean postRoom(RoomDto roomDto){
        try {
            Room room = new Room();

            room.setName(roomDto.getName());
            room.setPrice(roomDto.getPrice());
            room.setType(roomDto.getType());
            room.setAvailable(true);

            roomRepository.save(room);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public  RoomResponseDto getAllRoom(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 6);
       Page<Room> roomPage = roomRepository.findAll(pageable);

       RoomResponseDto roomResponseDto = new RoomResponseDto();
       roomResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
       roomResponseDto.setTotalPages(roomPage.getTotalPages());
       roomResponseDto.setRoomDtoList(roomPage.stream().map(Room::geRoomDto).collect(Collectors.toList()));

       return  roomResponseDto;

    }

    public RoomDto getRoomById(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if(optionalRoom.isPresent()){
            return optionalRoom.get().geRoomDto();

        }else {
            throw new EntityNotFoundException("Room not present");
        }
    }

    public boolean updateRoom(Long id, RoomDto roomDto){
        Optional<Room> optionalRoom = roomRepository.findById(id);
         if (optionalRoom.isPresent()) {
            Room existingRoom  = optionalRoom.get();

            existingRoom.setName(roomDto.getName());
            existingRoom.setPrice(roomDto.getPrice());
            existingRoom.setType(roomDto.getType());

            roomRepository.save(existingRoom);
            return true;
         }
         return false;
    }

    public void deleteRoom(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            roomRepository.deleteById(id);
        }
        else {
            throw new EntityNotFoundException("Room not present.");
        }
    }

}
