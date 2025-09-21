package com.hotelmangement.hotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmangement.hotel.entities.User;
import com.hotelmangement.hotel.entities.UserRole;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findFirstByEmail(String email);
    Optional<User> findByUserRole(UserRole userRole);
}
