package com.sparta.springtrello.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    // 코드 예시
    // 중복 : DUPLICATED_BOARD
    // 불일치 : INVALID_

    // 공통
    SUCCESS("정상 처리되었습니다."),


    // 사용자
    NOT_FOUND_USER("해당 사용자는 존재하지 않습니다."),
    NOT_FOUND_CARD("해당 카드는 존재하지 않습니다."),
    NOT_FOUND_LIST("해당 카드는 존재하지 않습니다."),
    INVALID_USER_AUTHORITY("해당 사용자 권한은 유효하지 않습니다."),

    DUPLICATE_EMAIL("이미 존재하는 이메일입니다."),
    INVALID_EMAIL("존재하지 않는 이메일입니다."),
    INVALID_PASSWORD("비밀번호가 일치하지 않습니다."),



    // JWT 관련 예외
    INVALID_JWT_TOKEN("유효하지 않은 JWT 토큰입니다."),
    EXPIRED_JWT_TOKEN("JWT 토큰이 만료되었습니다."),

    // 보드
    NOT_FOUND_BOARD("해당 보드는 존재하지 않습니다");
    private final String message;
}
