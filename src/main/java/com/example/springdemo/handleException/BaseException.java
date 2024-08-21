package com.example.springdemo.handleException;

import com.example.springdemo.common.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseException extends RuntimeException {
    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    private ErrorCode errorCode;

}
