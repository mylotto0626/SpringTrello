package com.sparta.springtrello.common.enums;

import com.sparta.springtrello.common.exception.InvalidParameterException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.sparta.springtrello.common.exception.ResponseCode.INVALID_USER_AUTHORITY;

@Getter
@RequiredArgsConstructor
public enum Authority {
    USER("유저"),  // 사용자 권한
    ADMIN("관리자");  // 관리자 권한

    private final String authority;

    public static Authority getAuthority(String authorityName) {
        for (Authority authority : Authority.values()) {
            if (authority.getAuthority().equals(authorityName)) {
                return authority;
            }
        }

        throw new InvalidParameterException(INVALID_USER_AUTHORITY);
    }
}
