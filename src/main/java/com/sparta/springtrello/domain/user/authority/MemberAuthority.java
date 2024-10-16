package com.sparta.springtrello.domain.user.authority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;


@Getter
@RequiredArgsConstructor
public enum MemberAuthority {

    READ_ONLY(UserAuthority.READONLY),
    WORKSPACE(UserAuthority.WORKSPACE),
    BOARD(UserAuthority.BOARD);

    private final String memberAuthority;

    public static Authority of(String role) {
        return Arrays.stream(Authority.values())
                .filter(r -> r.name().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 UserRole"));
    }
    public static class UserAuthority {
        public static final String READONLY = "ROLE_READONLY";
        public static final String WORKSPACE = "ROLE_WORKSPACE";
        public static final String BOARD = "ROLE_BOARD";

    }

}
