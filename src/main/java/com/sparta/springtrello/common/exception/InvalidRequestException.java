package com.sparta.springtrello.common.exception;

public class InvalidRequestException extends IllegalArgumentException{
    public InvalidRequestException(String message) {
        super(message);
    }
}
