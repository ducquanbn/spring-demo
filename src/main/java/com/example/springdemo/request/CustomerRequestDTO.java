package com.example.springdemo.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRequestDTO {

    @NotEmpty(message = "userName is not empty")
    private String userName;
    private String phoneNumber;
    private Integer age;
    @NotEmpty(message = "password is not empty")
    private String password;
}
