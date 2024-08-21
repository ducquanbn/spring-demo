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

    @GetMapping("/{id}")
    public BaseResponse<CustomerResponseDTO> getCustomer(@PathVariable Long id) {
        return BaseResponse.<CustomerResponseDTO>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .message(ErrorCode.SUCCESS.getMessage())
                .data(customerService.findById(id))
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

    @PutMapping("/update/{id}")
    public BaseResponse<CustomerResponseDTO> updateCustomer(@PathVariable long id, @Validated @RequestBody CustomerRequestDTO request) {
        customerService.updateCustomer(id, request);
        return BaseResponse.<CustomerResponseDTO>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .message(ErrorCode.SUCCESS.getMessage())
                .build();
    }

}
