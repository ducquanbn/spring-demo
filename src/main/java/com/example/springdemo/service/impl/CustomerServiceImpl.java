package com.example.springdemo.service.impl;

import com.example.springdemo.common.ErrorCode;
import com.example.springdemo.common.Role;
import com.example.springdemo.entity.CustomerEntity;
import com.example.springdemo.handleException.BaseException;
import com.example.springdemo.repository.CustomerRepository;
import com.example.springdemo.request.CustomerRequestDTO;
import com.example.springdemo.response.dto.CustomerResponseDTO;
import com.example.springdemo.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<CustomerResponseDTO> findAll() {
        List<CustomerEntity> customerEntityList = customerRepository.findAll();
        if (customerEntityList.isEmpty()) {
            throw new BaseException(ErrorCode.NOT_FOUND);
        }

        List<CustomerResponseDTO> customerList = new ArrayList<>();
        customerEntityList.forEach(data ->
            customerList.add(mapEntity(data))
        );

        return customerList;
    }

    @Override
    public CustomerResponseDTO findByUsername(String username) {
        Optional<CustomerEntity> customer = Optional.ofNullable(customerRepository.findByUsername(username));
        if (customer.isEmpty()) {
            throw new BaseException(ErrorCode.NOT_FOUND);
        }

        return mapEntity(customer.get());
    }

    @Override
    public void createCustomer(CustomerRequestDTO request) {

        CustomerEntity customer = customerRepository.findByUsername(request.getUsername());
        if (customer != null) {
            throw new BaseException(ErrorCode.USER_EXISTS);
        }

        Set<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        customerRepository.save(CustomerEntity.builder()
                .username(request.getUsername())
                .phoneNumber(request.getPhoneNumber())
                .age(request.getAge())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build());
    }

    @Override
    public void updateCustomer(String username, CustomerRequestDTO request) {
        if (username == null) {
            throw new BaseException(ErrorCode.NOT_FOUND);
        }

        Optional<CustomerEntity> customer = Optional.ofNullable(customerRepository.findByUsername(username));
        if (customer.isEmpty()) {
            throw new BaseException(ErrorCode.NOT_FOUND);
        }

        CustomerEntity customerEntity = CustomerEntity.builder()
                .id(customer.get().getId())
                .username(customer.get().getUsername())
                .phoneNumber(request.getPhoneNumber())
                .age(request.getAge())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        customerRepository.save(customerEntity);
    }

    private CustomerResponseDTO mapEntity(CustomerEntity customerEntity) {
        return CustomerResponseDTO.builder()
                .id(customerEntity.getId())
                .username(customerEntity.getUsername())
                .phoneNumber(customerEntity.getPhoneNumber())
                .age(customerEntity.getAge())
                .roles(customerEntity.getRoles())
                .build();
    }
}
