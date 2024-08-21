package com.example.springdemo.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse<T> {
    private String code;
    private String message;
    private T data;
}
