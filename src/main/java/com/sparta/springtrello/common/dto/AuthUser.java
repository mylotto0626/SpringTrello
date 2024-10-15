package com.sparta.springtrello.common.dto;

import com.sparta.springtrello.common.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthUser {
    private Long userId;
    private String email;
    private Authority authority;
}

