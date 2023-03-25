package com.example.sns.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

//파싱하기 쉽게 응답을 규격화
@Getter
@AllArgsConstructor
public class Response<T> {

    private String resultCode;
    private T result;

    public static Response<Void> error(String code){
        return new Response<>(code,null);
    }

    public static <T> Response<T> success(T result){
        return new Response<>("SUCCESS", result);
    }
}
