package com.example.springdemo.common;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum ErrorCode {
    SUCCESS("0","Success"),
    ERROR("9999","Error"),
    NOT_FOUND("0001", "Data not Found"),
    USER_EXISTS("0002", "User is exists"),
    CANT_UPDATE_USERNAME("0002", "Can't update username"),
    UNAUTHORIZED("0003", "wrong user or password"),
    WRONG_PASSWORD("0004", "wrong password")
    ;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
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
