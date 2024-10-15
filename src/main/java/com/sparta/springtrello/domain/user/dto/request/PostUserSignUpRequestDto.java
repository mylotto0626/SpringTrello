
package com.sparta.springtrello.domain.user.dto.request;

import com.sparta.springtrello.common.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class PostUserSignUpRequestDto {
    private String email;
    private String name;
    private String pw;
    private String phoneNumber;
    private Authority authority;
    private String zip;
    private String address;
    private String addressDetail;

}
