package com.hotelmangement.hotel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmangement.hotel.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    Page<Reservation> findAllByUserId(Pageable pageable, Long userId);
}
