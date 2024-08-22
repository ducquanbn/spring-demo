package com.example.springdemo.service;

import com.example.springdemo.request.LoginRequestDTO;
import com.example.springdemo.response.dto.AuthenticationResponseDTO;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface LoginService {
    AuthenticationResponseDTO login(LoginRequestDTO loginRequest);
    boolean verifyToken(String token) throws JOSEException, ParseException;
}
