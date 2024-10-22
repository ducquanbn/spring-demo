package com.example.springdemo.controller;

import com.example.springdemo.common.ErrorCode;
import com.example.springdemo.request.CustomerRequestDTO;
import com.example.springdemo.response.BaseResponse;
import com.example.springdemo.response.dto.CustomerResponseDTO;
import com.example.springdemo.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService;

    @GetMapping("")
    public BaseResponse<List<CustomerResponseDTO>> getAll() {
        return BaseResponse.<List<CustomerResponseDTO>>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .message(ErrorCode.SUCCESS.getMessage())
                .data(customerService.findAll())
                .build();
    }

    @GetMapping("/{username}")
    public BaseResponse<CustomerResponseDTO> getCustomer(@PathVariable String username) {
        return BaseResponse.<CustomerResponseDTO>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .message(ErrorCode.SUCCESS.getMessage())
                .data(customerService.findByUsername(username))
                .build();
    }

    @PostMapping("/create")
    public BaseResponse<CustomerResponseDTO> createCustomer(@Validated @RequestBody CustomerRequestDTO request) {
        customerService.createCustomer(request);
        return BaseResponse.<CustomerResponseDTO>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .message(ErrorCode.SUCCESS.getMessage())
                .build();
    }

    @PutMapping("/update/{username}")
    public BaseResponse<CustomerResponseDTO> updateCustomer(@PathVariable String username, @Validated @RequestBody CustomerRequestDTO request) {
        customerService.updateCustomer(username, request);
        return BaseResponse.<CustomerResponseDTO>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .message(ErrorCode.SUCCESS.getMessage())
                .build();
    }

}
