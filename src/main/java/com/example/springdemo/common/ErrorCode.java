package com.example.springdemo.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum ErrorCode {
    SUCCESS("0","Success", HttpStatus.OK),
    ERROR("9999","Error", HttpStatus.BAD_REQUEST),
    NOT_FOUND("0001", "Data not Found", HttpStatus.BAD_REQUEST),
    USER_EXISTS("0002", "User is exists", HttpStatus.BAD_REQUEST),
    CANT_UPDATE_USERNAME("0002", "Can't update username", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("0003", "unauthorized", HttpStatus.UNAUTHORIZED),
    WRONG_PASSWORD("0004", "wrong password", HttpStatus.UNAUTHORIZED)
    ;

    private final String code;
    private final String message;
    private final HttpStatusCode httpStatusCode;

    ErrorCode(String code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    private static final Map<ErrorCode, String> BY_CODE = new HashMap<>();
    private static final Map<ErrorCode, String> BY_MESSAGE = new HashMap<>();

    static {
        for (ErrorCode e : values()) {
            BY_CODE.put(e, e.code);
            BY_MESSAGE.put(e, e.message);
        }
    }
}
