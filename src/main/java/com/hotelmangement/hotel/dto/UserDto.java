package com.hotelmangement.hotel.dto;

import com.hotelmangement.hotel.entities.UserRole;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private UserRole userRole;

}
