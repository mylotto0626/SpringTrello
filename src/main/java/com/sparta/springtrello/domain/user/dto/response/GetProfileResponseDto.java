package com.sparta.springtrello.domain.user.dto.response;

import com.sparta.springtrello.common.enums.Authority;
import com.sparta.springtrello.domain.user.UserRole.UserRole;
import com.sparta.springtrello.entity.User;
import lombok.Getter;

@Getter
public class GetProfileResponseDto {
    private final String email;
    private final String nickName;
    private final UserRole userRole;

    public GetProfileResponseDto(User user) {
        this.email = user.getEmail();
        this.nickName = user.getNickName();
        this.userRole = user.getUserRole();
    }
}
