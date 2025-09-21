package com.hotelmangement.hotel.dto;

import com.hotelmangement.hotel.entities.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {
    
    private String jwt;

    private Long userId;

    private UserRole userRole;
}
