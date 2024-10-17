package com.sparta.springtrello.domain.user.dto.request;

import lombok.Getter;

@Getter
public class PostUserRecoveryRequestDto{
    private String email;
    private String phoneNumber;
}