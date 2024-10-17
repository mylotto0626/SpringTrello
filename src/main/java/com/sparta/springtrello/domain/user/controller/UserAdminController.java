package com.sparta.springtrello.domain.user.controller;


import com.sparta.springtrello.common.annotation.Auth;
import com.sparta.springtrello.common.dto.AuthUser;
import com.sparta.springtrello.domain.user.authority.Authority;
import com.sparta.springtrello.domain.user.dto.request.UserRoleChangeRequest;
import com.sparta.springtrello.domain.user.service.UserAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor

public class UserAdminController {
    private final UserAdminService userAdminService;

    @PatchMapping("/admin/users/{userId}")
    public void changeUserRole(@AuthenticationPrincipal AuthUser authUser, @PathVariable long userId, @RequestBody UserRoleChangeRequest userRoleChangeRequest) {
        userAdminService.changeUserRole(authUser, userId, userRoleChangeRequest);
    }
}