package com.example.momo.exception;

import com.example.momo.dto.response.BaseErrorResponse;
import com.example.momo.dto.response.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 Try-Catch로 예외처리를 할경우 코드가 길어진다 가독성을 좋게 하기위한 어노테이션
 단순 예외처리만 하고 싶다면 ControllerAdvice
 응답으로 객체를 리턴하고 싶다면 RestControllerAdvice를 적용
 * */
@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**
     메서드에 선언하고 특정 예외 클래스를 지정해주면 해당 예외가 발생했을 때
     메서드에 정의한 로직으로 처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseErrorResponse> handleNotFoundException(NotFoundException e){
        ErrorCode errorCode = ErrorCode.valueOf(e.getMessage());
        return new ResponseEntity<>(BaseErrorResponse.of(errorCode), HttpStatus.NOT_FOUND);
    }

}
