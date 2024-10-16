package com.sparta.springtrello.entity;

import com.sparta.springtrello.domain.card.entity.Card;
import com.sparta.springtrello.domain.user.authority.Authority;
import com.sparta.springtrello.domain.user.authority.MemberAuthority;
import com.sparta.springtrello.domain.user.dto.request.PatchUserRequestDto;
import com.sparta.springtrello.domain.user.dto.request.PostUserSignUpRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Enumerated(EnumType.STRING)
    private MemberAuthority memberAuthority;

    private String refreshToken;
    private String nickName;

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();
    // 탈퇴여부
    @Column(nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "user")
    private List<Card> cards = new ArrayList<>();

    public User(PostUserSignUpRequestDto requestDto,Authority authority, String pw) {
        this.email = requestDto.getEmail();
        this.nickName = requestDto.getNickName();
        this.pw = pw;
        this.authority = authority;
        this.memberAuthority = MemberAuthority.WORKSPACE;
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
