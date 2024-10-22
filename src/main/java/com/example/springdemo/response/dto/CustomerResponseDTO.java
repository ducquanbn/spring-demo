package com.example.springdemo.response.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class CustomerResponseDTO {
    private String id;
    private String username;
    private String phoneNumber;
    private Integer age;
    private String password;
    private Set<String> roles;
}
