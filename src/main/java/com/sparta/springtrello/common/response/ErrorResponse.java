package com.sparta.springtrello.common.response;

import com.sparta.springtrello.common.exception.ApiException;
import lombok.Getter;

@Getter
public class ErrorResponse extends BaseResponse {
    private ErrorResponse(ApiException apiException) {
        super(apiException.getHttpStatus().value(), apiException.getMessage());
    }

    private ErrorResponse(int code, String message) {
        super(code, message);
    }

    public static ErrorResponse from(ApiException apiException) {
        return new ErrorResponse(apiException);
    }

    public static ErrorResponse of(int code, String message) {
        return new ErrorResponse(code, message);
    }
}