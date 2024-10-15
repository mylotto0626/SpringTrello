package com.sparta.springtrello.entity;

import com.sparta.springtrello.common.enums.Authority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String email;

    @Column
    @NotNull
    private String pw;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Enumerated(EnumType.STRING)
    private Authority memberAuthority;

    private String refreshToken;
    private String nickName;

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Card> cards = new ArrayList<>();


    private User(String email, String pw, Authority authority, Authority memberAuthority, String refreshToken, String nickName) {
        this.email = email;
        this.pw = pw;
        this.memberAuthority = memberAuthority;
        this.refreshToken = refreshToken;
        this.nickName = nickName;
    }
}