package com.example.springdemo.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRequestDTO {

    @NotEmpty(message = "username is not empty")
    private String username;
    private String phoneNumber;
    private Integer age;
    @NotEmpty(message = "password is not empty")
    private String password;
}
