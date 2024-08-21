package com.example.springdemo.handleException;

import com.example.springdemo.common.ErrorCode;
import com.example.springdemo.response.BaseResponse;
import jakarta.xml.bind.ValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = BaseException.class)
    public BaseResponse<BaseException> baseExceptionHandler(BaseException e) {
        return BaseResponse.<BaseException>builder()
                .code(e.getErrorCode().getCode())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse<BaseException> defaultErrorHandler(Exception e) {
      return BaseResponse.<BaseException>builder()
                .code(ErrorCode.ERROR.getCode())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResponse<BaseException> defaultErrorHandler(MethodArgumentNotValidException e) {
        return BaseResponse.<BaseException>builder()
                .code(ErrorCode.ERROR.getCode())
                .message(Objects.requireNonNull(e.getFieldError()).getDefaultMessage())
                .build();
    }
}
