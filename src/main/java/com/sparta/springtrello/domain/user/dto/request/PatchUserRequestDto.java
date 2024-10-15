package com.sparta.springtrello.domain.user.dto.request;

import com.sparta.springtrello.common.enums.Authority;
import lombok.Getter;


@Getter
public class PatchUserRequestDto {
    private String pw;
    private String newPw;
    private Authority authority;
    private String phoneNumber;
    private String zip;
    private String address;
    private String addressDetail;
}
