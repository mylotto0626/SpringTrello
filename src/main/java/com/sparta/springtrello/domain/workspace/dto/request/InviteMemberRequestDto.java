package com.sparta.springtrello.domain.workspace.dto.request;

import com.sparta.springtrello.domain.user.authority.Authority;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InviteMemberRequestDto {

    @Email
    private String email;
    private String role;

}

