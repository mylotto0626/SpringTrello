package com.sparta.springtrello.domain.user.controller;



import com.sparta.springtrello.common.dto.AuthUser;
import com.sparta.springtrello.common.dto.ResponseDto;
import com.sparta.springtrello.domain.user.dto.request.UserRoleChangeRequest;
import com.sparta.springtrello.domain.user.service.UserAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class UserAdminController {
    private final UserAdminService userAdminService;

    @PatchMapping("/admin/users/{userId}")
    public ResponseEntity<ResponseDto<String>> changeUserRole(@AuthenticationPrincipal AuthUser authUser, @PathVariable long userId, @RequestBody UserRoleChangeRequest userRoleChangeRequest) {
        userAdminService.changeUserRole(authUser, userId, userRoleChangeRequest);
        return ResponseEntity.ok(new ResponseDto<>(HttpStatus.OK.value() , null , "권한 변경 성공."));
    }
}