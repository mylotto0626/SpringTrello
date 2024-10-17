
package com.sparta.springtrello.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserSignUpRequestDto {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String pw;
    @NotBlank
    private String userRole;
    @NotBlank
    private String nickName;
}
