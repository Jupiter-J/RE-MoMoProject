package com.example.momo.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponse<T> {
    private final String access;
    private final T data;

    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>("OK",data);
    }

}
