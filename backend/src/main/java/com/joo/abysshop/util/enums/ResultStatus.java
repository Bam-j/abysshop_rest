package com.joo.abysshop.util.enums;

public enum ResultStatus {
    //일반 요청에 대한 성공 & 실패
    SUCCESS,
    FAILURE,

    //잘못된 요청의 실패 상태
    BAD_REQUEST,
    EMPTY_INPUT_FORM,

    //계정 정보 입력 관련 실패 상태
    INVALID_USERNAME,
    INVALID_PASSWORD,
    DUPLICATE_USERNAME,
    DUPLICATE_NICKNAME,
    SAME_NICKNAME,
    SAME_PASSWORD,

    //주문 관련 실패 상태
    INSUFFICIENT_POINTS
}
