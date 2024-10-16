package com.sparta.springtrello.domain.user.dto.request;


import com.sparta.springtrello.domain.user.authority.UserRole;
import lombok.Getter;


@Getter
public class PatchUserRequestDto {
    private String pw;
    private String newPw;
    private UserRole userRole;

}
