package com.example.courseappspringboot.application.auth;

import com.example.courseappspringboot.application.auth.dto.AuthenticationRequest;
import com.example.courseappspringboot.application.auth.dto.AuthenticationResponse;
import com.example.courseappspringboot.application.auth.dto.RegistrationDto;
import com.example.courseappspringboot.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register_user(@RequestBody RegistrationDto request){

       return ResponseEntity.ok(service.register_user(request));

    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));


    }

}
