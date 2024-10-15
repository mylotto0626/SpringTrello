package com.sparta.springtrello.entity;

import com.sparta.springtrello.common.enums.Authority;
import com.sparta.springtrello.domain.user.dto.request.PatchUserRequestDto;
import com.sparta.springtrello.domain.user.dto.request.PostUserSignUpRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;
    @Column(nullable = false)
    private String nickName;
    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    // 탈퇴여부
    @Column(nullable = false)
    private boolean status;


    public User(PostUserSignUpRequestDto requestDto, String pw) {
        this.email = requestDto.getEmail();
        this.nickName = requestDto.getName();
        this.pw = pw;
        this.authority = requestDto.getAuthority();
        this.status = true;
    }

    public void delete() {
        this.status = false;
    }

    public void update(String pw, PatchUserRequestDto requestDto) {
        this.authority = requestDto.getAuthority() != null ? requestDto.getAuthority() : this.authority;
        this.pw = requestDto.getPw() != null ? pw : this.pw;
    }
}
