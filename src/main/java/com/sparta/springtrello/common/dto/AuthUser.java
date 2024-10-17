package com.sparta.springtrello.common.dto;


import com.sparta.springtrello.domain.user.authority.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class AuthUser {

    private final Long id;
    private final String email;
    private final String nickName;
    private final Collection<? extends GrantedAuthority> authorities;


    public AuthUser(Long id, String email,String nickName, Authority role) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.authorities = List.of(new SimpleGrantedAuthority(role.name()));
    }
}
