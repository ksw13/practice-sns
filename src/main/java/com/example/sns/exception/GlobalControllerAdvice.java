package com.example.sns.exception;

import com.example.sns.controller.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//얘가 throw 잡아서 실패할때 errorCode,message 리턴하게 함
@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(SnsApplicationException.class)
    public ResponseEntity<?> applicationHandler(SnsApplicationException e) {
        log.error("Error occurs {}", e.toString());
        return  ResponseEntity.status(e.getErrorCode().getStatus())
                //공통 응답 class Response 사용
                .body(Response.error(e.getErrorCode().name()));
    }

    //SnsApplicationException 이외의 에러를 처리하기 위한 용도
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> applicationHandler(RuntimeException e) {
        log.error("Error occurs {}", e.toString());
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(ErrorCode.INTERNAL_SERVER_ERROR.getMessage()));
    }
}
