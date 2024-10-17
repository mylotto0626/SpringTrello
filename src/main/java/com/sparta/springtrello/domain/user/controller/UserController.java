package com.sparta.springtrello.domain.user.controller;



import com.sparta.springtrello.common.dto.AuthUser;
import com.sparta.springtrello.common.dto.ResponseDto;
import com.sparta.springtrello.domain.user.dto.request.*;
import com.sparta.springtrello.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    //회원가입 sign up
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<String>> signUpUser(@RequestBody PostUserSignUpRequestDto postUserSignUpRequestDto){
        userService.signUpUser(postUserSignUpRequestDto);
        return ResponseEntity.ok(new ResponseDto<>(HttpStatus.OK.value() , null , "회원가입에 성공했습니다"));
    }

    //로그인 sign in
    @PostMapping("/signin")
    public ResponseEntity<ResponseDto<String>> signInUser(@Valid @RequestBody PostUserSignInRequestDto postUserSignInRequestDto, HttpServletResponse response){
        String token = userService.signInUser(postUserSignInRequestDto);
        response.addHeader("ACCESS_TOKEN",token);
        return ResponseEntity.ok(new ResponseDto<>(HttpStatus.OK.value() , null , "로그인에 성공했습니다."));
    }

    //회원탈퇴 delete
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto<String>> deleteUser(@AuthenticationPrincipal AuthUser authUser, @RequestBody DeleteUserRequestDto deleteUserRequestDto){
        userService.deleteUser(authUser,deleteUserRequestDto);
        return ResponseEntity.ok(new ResponseDto<>(HttpStatus.OK.value() , null , "회원 탈퇴하였습니다."));
    }
}