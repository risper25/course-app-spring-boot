package com.example.courseappspringboot.application.auth.dto;

public record RegistrationDto(
        String first_name,
        String last_name,
        String email,

         String phone_number,
        String password
      ) { }
//"first_name":"risper","last_name":"orawo","email":"r@g.com","phone_number":"33","password":"1"