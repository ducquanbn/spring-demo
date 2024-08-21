package com.example.springdemo.response.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerResponseDTO {
    private Long id;
    private String userName;
    private String phoneNumber;
    private Integer age;
    private String password;
}
