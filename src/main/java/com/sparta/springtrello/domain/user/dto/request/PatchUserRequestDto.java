package com.sparta.springtrello.domain.user.dto.request;

import com.sparta.springtrello.common.enums.Authority;
import com.sparta.springtrello.domain.user.UserRole.UserRole;
import com.sparta.springtrello.entity.User;
import lombok.Getter;


@Getter
public class PatchUserRequestDto {
    private String pw;
    private String newPw;
    private UserRole userRole;

}
