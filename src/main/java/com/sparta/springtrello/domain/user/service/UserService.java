package com.sparta.springtrello.domain.user.service;

import com.sparta.springtrello.common.config.JwtUtil;
import com.sparta.springtrello.common.config.PasswordEncoder;
import com.sparta.springtrello.common.dto.AuthUser;
import com.sparta.springtrello.common.exception.*;
import com.sparta.springtrello.domain.user.authority.Authority;
import com.sparta.springtrello.domain.user.dto.request.*;
import com.sparta.springtrello.domain.user.dto.response.GetProfileResponseDto;
import com.sparta.springtrello.domain.user.repository.UserRepository;
import com.sparta.springtrello.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encode;
    private final JwtUtil jwtUtil;

    @Transactional
    //회원가입 signUp
    public void signUpUser(PostUserSignUpRequestDto postUserSignUpRequestDto) {
        // 이메일 중복 확인
        boolean overlap = userRepository.existsByEmail(postUserSignUpRequestDto.getEmail());
        if (overlap) {
            throw new DuplicateException(ResponseCode.DUPLICATE_EMAIL);
        }
        //비밀번호 벨류체크
        if(!encode.passwordVerification(postUserSignUpRequestDto.getPw())){
            throw new IllegalArgumentException("올바르지 않은 비밀번호 형식입니다.");
        }
        // 비밀번호 암호화
        String pw = encode.encode(postUserSignUpRequestDto.getPw());

        Authority authority = Authority.of(postUserSignUpRequestDto.getUserRole());

        User user = new User(postUserSignUpRequestDto,authority,pw);
        userRepository.save(user);
    }

    //로그인
    @Transactional
    public String signInUser(PostUserSignInRequestDto postUserSignUpRequestDto) {

        User user = userRepository.findByEmail(postUserSignUpRequestDto.getEmail()).orElseThrow(()-> new NotFoundException(ResponseCode.NOT_FOUND_USER));
        //정적 펙토리 메소드를 사용하여 user를 찾아온다 // stateless 무상태 서버가 상태를 가지지 않는다.
        checkPw(postUserSignUpRequestDto.getPw(), user.getPw(), user.getId());

        checkStatus(user);

        return jwtUtil.createToken(user.getId(), user.getEmail(),user.getAuthority(),user.getNickName());
    }

    //회원 탈퇴
    @Transactional
    public void deleteUser(AuthUser authUser, DeleteUserRequestDto deleteReqestDto) {
        Long id = authUser.getId();
        User user = findUser(id);
        //비밀번호 체크
        String pw= deleteReqestDto.getPw();
        checkPw(pw, user.getPw(), user.getId());
        //유저 비활성화 코드
        user.delete();
    }

    //pw가 틀렸을때 예외처리
    @Transactional
    public void checkPw(String userPw, String enPw,Long userId) {
        User user = findUser(userId);
        if (!encode.matches(userPw, enPw)) {
            throw new InvalidParameterException(ResponseCode.INVALID_PASSWORD);
        }
    }

    //회원조회
    public GetProfileResponseDto getProfile(AuthUser authUser) {
        Long id = authUser.getId();
        User user = findUser(id);
        return new GetProfileResponseDto(user);
    }

    //id를 사용한 유저 찾기
    public User findUser(Long userId) {
        return userRepository.findByIdAndStatusTrue(userId)
                .orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_USER));
    }

    //email값에 맞는 동일한 유저 찾기
    public User findByEmailUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_USER));
    }

    //회원 탈퇴 상태일때 로그인시도 예외처리
    public void checkStatus(User user){
        if(!user.isStatus()){
            throw new UnauthorizedException(ResponseCode.NOT_FOUND_USER);
        }
    }
}