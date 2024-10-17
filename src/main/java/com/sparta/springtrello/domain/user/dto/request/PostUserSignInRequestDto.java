package com.sparta.springtrello.domain.user.dto.request;

import lombok.Getter;

@Getter
public class PostUserSignInRequestDto {
    private String email;
    private String pw;
    private String userRole;
}