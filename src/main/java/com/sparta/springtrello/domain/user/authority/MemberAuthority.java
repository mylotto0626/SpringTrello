package com.sparta.springtrello.domain.user.authority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;


@Getter
@RequiredArgsConstructor
public enum MemberAuthority {

    READ_ONLY(MemberAuthority.UserMemberAuthority.READONLY),
    WORKSPACE(MemberAuthority.UserMemberAuthority.WORKSPACE),
    BOARD(MemberAuthority.UserMemberAuthority.BOARD);

    private final String Authority;

    public static MemberAuthority of(String role) {
        return Arrays.stream(MemberAuthority.values())
                .filter(r -> r.name().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 UserRole"));
    }
    public static class UserMemberAuthority {
        public static final String READONLY = "ROLE_READONLY";
        public static final String WORKSPACE = "ROLE_WORKSPACE";
        public static final String BOARD = "ROLE_BOARD";

    }

}
