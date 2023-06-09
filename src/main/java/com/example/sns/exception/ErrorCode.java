package com.example.sns.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    DUPLICATED_USER_NAME(HttpStatus.CONFLICT,"userName is duplicated"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"user is not found"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Password is invalid")
    ;

    private HttpStatus status;
    private String message;
}
