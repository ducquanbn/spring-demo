package com.example.springdemo.handleException;

import com.example.springdemo.common.ErrorCode;
import com.example.springdemo.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<BaseResponse<BaseException>> baseExceptionHandler(BaseException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatusCode())
                .body(BaseResponse.<BaseException>builder()
                    .code(e.getErrorCode().getCode())
                    .message(e.getMessage())
                    .build());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<BaseResponse<BaseException>> defaultErrorHandler(Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(BaseResponse.<BaseException>builder()
                .code(ErrorCode.ERROR.getCode())
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<BaseException>> defaultErrorHandler(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.<BaseException>builder()
                .code(ErrorCode.ERROR.getCode())
                .message(Objects.requireNonNull(e.getFieldError()).getDefaultMessage())
                .build());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<BaseResponse<BaseException>> accessDenied(AccessDeniedException e) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(BaseResponse.<BaseException>builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }
}
