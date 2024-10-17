package com.sparta.springtrello.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseResponse {
    private final int code;
    private final String message;

}
