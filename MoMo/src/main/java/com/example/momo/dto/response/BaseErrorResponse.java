package com.example.momo.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseErrorResponse {
    private final String code;
    private final String message;

    public static BaseErrorResponse of(ErrorCode errorCode){
        return new BaseErrorResponse(errorCode.getCode(), errorCode.getMessage());
    }

}
