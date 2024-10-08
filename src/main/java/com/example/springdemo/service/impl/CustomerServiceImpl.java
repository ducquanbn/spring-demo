package com.example.springdemo.service.impl;

import com.example.springdemo.common.ErrorCode;
import com.example.springdemo.entity.CustomerEntity;
import com.example.springdemo.handleException.BaseException;
import com.example.springdemo.repository.CustomerRepository;
import com.example.springdemo.request.CustomerRequestDTO;
import com.example.springdemo.response.dto.CustomerResponseDTO;
import com.example.springdemo.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerResponseDTO> findAll() {
        List<CustomerEntity> customerEntityList = customerRepository.findAll();
        if (customerEntityList.isEmpty()) {
            throw new BaseException(ErrorCode.NOT_FOUND);
        }

        List<CustomerResponseDTO> customerList = new ArrayList<>();
        customerEntityList.forEach(data -> {
            customerList.add(mapEntity(data));
        });

        return customerList;
    }

    @Override
    public CustomerResponseDTO findById(Long id) {
        Optional<CustomerEntity> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new BaseException(ErrorCode.NOT_FOUND);
        }

        return mapEntity(customer.get());
    }

    @Override
    public void createCustomer(CustomerRequestDTO request) {

        CustomerEntity customer = customerRepository.findByUserName(request.getUserName());
        if (customer != null) {
            throw new BaseException(ErrorCode.USER_EXISTS);
        }

        customerRepository.save(CustomerEntity.builder()
                .userName(request.getUserName())
                .phoneNumber(request.getPhoneNumber())
                .age(request.getAge())
                .password(request.getPassword())
                .build());
    }

    @Override
    public void updateCustomer(Long id, CustomerRequestDTO request) {
        if (id == null) {
            throw new BaseException(ErrorCode.NOT_FOUND);
        }

        Optional<CustomerEntity> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new BaseException(ErrorCode.NOT_FOUND);
        }
        CustomerEntity customerEntity = CustomerEntity.builder()
                .id(customer.get().getId())
                .userName(customer.get().getUserName())
                .password(customer.get().getPhoneNumber())
                .age(customer.get().getAge())
                .password(customer.get().getPassword())
                .build();
        customerRepository.save(customerEntity);
    }

    private CustomerResponseDTO mapEntity(CustomerEntity customerEntity) {
        return CustomerResponseDTO.builder()
                .id(customerEntity.getId())
                .userName(customerEntity.getUserName())
                .phoneNumber(customerEntity.getPhoneNumber())
                .age(customerEntity.getAge())
                .password(customerEntity.getPassword())
                .build();
    }
}
