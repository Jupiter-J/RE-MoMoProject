package com.example.momo.exception;


import com.example.momo.dto.response.ErrorCode;

public class NotFoundException extends RuntimeException{
    public NotFoundException(ErrorCode errorCode){
        super(errorCode.name());
    }
}
