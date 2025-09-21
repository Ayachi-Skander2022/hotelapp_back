package com.hotelmangement.hotel.services.auth;

import com.hotelmangement.hotel.dto.SignupRequest;
import com.hotelmangement.hotel.dto.UserDto;

public interface AuthService {

    
UserDto createUser(SignupRequest signupRequest);
    
}
