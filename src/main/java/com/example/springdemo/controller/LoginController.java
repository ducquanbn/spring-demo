package com.example.springdemo.controller;

import com.example.springdemo.common.ErrorCode;
import com.example.springdemo.request.LoginRequestDTO;
import com.example.springdemo.response.BaseResponse;
import com.example.springdemo.response.dto.AuthenticationResponseDTO;
import com.example.springdemo.service.impl.LoginServiceImpl;
import com.nimbusds.jose.JOSEException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

    private final LoginServiceImpl loginService;

    public LoginController(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/verify")
    public BaseResponse<Boolean> verifyToken(@RequestParam("token") String token) throws ParseException, JOSEException {
        return BaseResponse.<Boolean>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .message(ErrorCode.SUCCESS.getMessage())
                .data(loginService.verifyToken(token))
                .build();
    }

    @PostMapping("")
    public BaseResponse<AuthenticationResponseDTO> login(@Validated @RequestBody LoginRequestDTO request) {
        return BaseResponse.<AuthenticationResponseDTO>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .message(ErrorCode.SUCCESS.getMessage())
                .data(loginService.login(request))
                .build();
    }


}
