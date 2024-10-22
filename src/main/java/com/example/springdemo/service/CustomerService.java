package com.example.springdemo.service;


import com.example.springdemo.request.CustomerRequestDTO;
import com.example.springdemo.response.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseDTO> findAll() throws Exception;
    CustomerResponseDTO findByUsername(String username) throws Exception;
    void createCustomer(CustomerRequestDTO request);
    void updateCustomer(String id,CustomerRequestDTO request);
}
