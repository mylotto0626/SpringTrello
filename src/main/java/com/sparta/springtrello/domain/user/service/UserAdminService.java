package com.sparta.springtrello.domain.user.service;

import com.sparta.springtrello.common.exception.InvalidRequestException;
import com.sparta.springtrello.domain.user.authority.Authority;
import com.sparta.springtrello.domain.user.authority.MemberAuthority;
import com.sparta.springtrello.domain.user.dto.request.UserRoleChangeRequest;
import com.sparta.springtrello.domain.user.repository.UserRepository;
import com.sparta.springtrello.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAdminService {

    private final UserRepository userRepository;

    @Transactional
    public void changeUserRole(long userId, UserRoleChangeRequest userRoleChangeRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new InvalidRequestException("User not found"));
        user.updateRole(MemberAuthority.of(userRoleChangeRequest.getRole()));
    }
}
