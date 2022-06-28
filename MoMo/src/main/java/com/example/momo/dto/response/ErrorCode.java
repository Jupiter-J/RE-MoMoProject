package com.example.momo.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    IS_DELETED_CATEGORY("2021","카테고리를 삭제하였습니다"),

    //Not Found
    NOT_FOUND_CATEGORY_EXCEPTION("4040", "존재하지 않는 카테고리입니다"),
    NOT_FOUND_CATEGORY_CONTENTS("4041", "카테고리 내용을 입력하세요"),
    MISS_INPUT_CATEGORY_ID("4042","카테고리 아이디가 일치하지 않습니다"),
    NOT_FOUND_QUESTION_EXCEPTION("4050","존재하지 않는 질문입니다"),


    //Server Error
    INTERNAL_SERVER_EXCEPTION("5050", "서버 내부 에러입니다. 관리자에게 문의하세요.");




    private final String code;
    private final String message;

}
