package com.sparta.springtrello.domain.user.dto.request;

import lombok.Getter;
import org.sparta.outsourcingproject.domain.user.Authority;

@Getter
public class PostUserSignInRequestDto {
    private String email;
    private String pw;
    private Authority authority;
}