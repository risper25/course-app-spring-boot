package com.example.courseappspringboot.application.auth;

import com.example.courseappspringboot.application.auth.dto.AuthenticationRequest;
import com.example.courseappspringboot.application.auth.dto.AuthenticationResponse;
import com.example.courseappspringboot.application.auth.dto.RegistrationDto;
import com.example.courseappspringboot.domain.dao.user.UserDaoImpl;
import com.example.courseappspringboot.domain.model.user.Role;
import com.example.courseappspringboot.domain.model.user.User;
import com.example.courseappspringboot.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.courseappspringboot.domain.model.user.User.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDaoImpl userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register_user(RegistrationDto request){

        var user= User.builder()
                .first_name(request.first_name())
                .last_name(request.last_name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phone_number(request.phone_number())
                .role(Role.USER)
                .build();

        userDao.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user=userDao.getUserByEmail(request.getEmail());//TODO: add exception handling

        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }

}
