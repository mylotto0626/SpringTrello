package com.sparta.springtrello.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {
    private int status;
    private T body;
    private String message;
}

