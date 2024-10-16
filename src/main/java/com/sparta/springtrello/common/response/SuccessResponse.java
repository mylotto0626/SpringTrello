package com.sparta.springtrello.common.response;

import com.sparta.springtrello.domain.board.dto.response.BoardUpdateResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import com.sparta.springtrello.common.exception.ResponseCode;

@Getter
public class SuccessResponse<T> extends BaseResponse {
    private T data;

    private SuccessResponse(T data) {
        super(HttpStatus.OK.value(), ResponseCode.SUCCESS.getMessage());
        this.data = data;
    }

    public static <T> SuccessResponse<T> of(T data) {
        return new SuccessResponse<>(data);
    }
}