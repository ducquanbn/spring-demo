package com.example.springdemo.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDTO {

    @NotEmpty(message = "username is not empty")
    private String username;

    @NotEmpty(message = "password is not empty")
    private String password;
}
