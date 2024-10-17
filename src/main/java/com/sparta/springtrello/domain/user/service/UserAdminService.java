package com.sparta.springtrello.domain.user.service;


import com.sparta.springtrello.common.dto.AuthUser;
import com.sparta.springtrello.common.exception.InvalidRequestException;
import com.sparta.springtrello.common.exception.ResponseCode;
import com.sparta.springtrello.common.exception.UnauthorizedException;
import com.sparta.springtrello.domain.user.authority.Authority;
import com.sparta.springtrello.domain.user.authority.MemberAuthority;
import com.sparta.springtrello.domain.user.dto.request.UserRoleChangeRequest;
import com.sparta.springtrello.domain.user.repository.UserRepository;
import com.sparta.springtrello.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserAdminService {

    private final UserRepository userRepository;

    @Transactional
    public void changeUserRole(AuthUser authUser, long userId, UserRoleChangeRequest userRoleChangeRequest) {

        User user = userRepository.findById(userId).orElseThrow(() -> new InvalidRequestException("User not found"));

        if(!authUser.getAuthorities().equals(Authority.of(userRoleChangeRequest.getMemberRole()))){
            throw new UnauthorizedException(ResponseCode.INVALID_USER_AUTHORITY);
        }
        user.updateRole(MemberAuthority.of(userRoleChangeRequest.getMemberRole()));
    }
}
