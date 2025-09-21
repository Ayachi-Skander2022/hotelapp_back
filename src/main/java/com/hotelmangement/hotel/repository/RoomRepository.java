package com.hotelmangement.hotel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmangement.hotel.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    
    Page<Room> findByAvailable(boolean available, Pageable pageable);
    
}
