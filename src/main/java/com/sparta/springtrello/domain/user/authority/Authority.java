package com.sparta.springtrello.domain.user.authority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Authority {

    ROLE_USER(Authority.UserAuthority.USER),
    ROLE_ADMIN(Authority.UserAuthority.ADMIN);
    private final String authority;

    public static com.sparta.springtrello.domain.user.authority.Authority of(String role) {
        return Arrays.stream(com.sparta.springtrello.domain.user.authority.Authority.values())
                .filter(r -> r.name().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 UserRole"));
    }
    public static class UserAuthority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
