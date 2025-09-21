package com.hotelmangement.hotel.controller.auth;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmangement.hotel.dto.AuthenticationRequest;
import com.hotelmangement.hotel.dto.AuthenticationResponse;
import com.hotelmangement.hotel.dto.SignupRequest;
import com.hotelmangement.hotel.dto.UserDto;
import com.hotelmangement.hotel.entities.User;
import com.hotelmangement.hotel.repository.UserRepository;
import com.hotelmangement.hotel.services.auth.AuthService;
import com.hotelmangement.hotel.services.jwt.UserService;
import com.hotelmangement.hotel.util.JwtUtil;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Authorization")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final UserService userService;
    
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        try {
            UserDto createUser = authService.createUser(signupRequest);
            return new ResponseEntity<>(createUser, HttpStatus.OK);
        }
        catch (EntityExistsException entityExistsException){
            return new ResponseEntity<>("User Already exists", HttpStatus.NOT_ACCEPTABLE);
        }
        catch (Exception e){
            return new ResponseEntity<>("User not created, come again later", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){
          try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
          } catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username o password.");
          }

          final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
          
          Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
          final String jwt = jwtUtil.generateToken((User) userDetails);


          AuthenticationResponse authenticationResponse = new AuthenticationResponse();

          if (optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
            authenticationResponse.setUserId(optionalUser.get().getId());
          }

          return authenticationResponse;
    }
}
