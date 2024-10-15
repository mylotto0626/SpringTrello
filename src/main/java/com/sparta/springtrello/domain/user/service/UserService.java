//package com.sparta.springtrello.domain.user.service;
//
//import com.sparta.springtrello.common.config.JwtUtil;
//import com.sparta.springtrello.common.config.PasswordEncoder;
//import com.sparta.springtrello.common.dto.AuthUser;
//import com.sparta.springtrello.domain.user.dto.request.*;
//import com.sparta.springtrello.domain.user.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//public class UserService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder encode;
//    private final JwtUtil jwtUtil;
//
//    //회원가입 signUp
//    public void signUpUser(PostUserSignUpRequestDto postUserSignUpRequestDto) {
//        // 이메일 중복 확인
//        boolean overlap = userRepository.existsByEmail(postUserSignUpRequestDto.getEmail());
//        if (overlap) {
//            throw new DuplicateEmailException(ErrorCode.DUPLICATE_EMAIL_ERROR);
//        }
//        userCheckService.checkHp(postUserSignUpRequestDto.getPhoneNumber());
//        //비밀번호 벨류체크
//        if(!encode.passwordVerification(postUserSignUpRequestDto.getPw())){
//            throw new IllegalArgumentException("올바르지 않은 비밀번호 형식입니다.");
//        }
//        // 비밀번호 암호화
//        String pw = encode.encode(postUserSignUpRequestDto.getPw());
//
//        User user = new User(postUserSignUpRequestDto, pw);
//        userRepository.save(user);
//    }
//
//    //로그인
//    @Transactional(noRollbackFor = MismatchPasswordException.class)
//    public String signInUser(PostUserSignInRequestDto postUserSignUpRequestDto) {
//
//        User user = userCheckService.findByEmailUser(postUserSignUpRequestDto.getEmail());
//        //정적 펙토리 메소드를 사용하여 user를 찾아온다 // stateless 무상태 서버가 상태를 가지지 않는다.
//        checkPw(postUserSignUpRequestDto.getPw(), user.getPw(), user.getId());
//
//        userCheckService.checkStatus(user);
//
//        return jwtUtil.createToken(user.getId(), user.getEmail(),user.getAuthority());
//    }
//
//    //회원 탈퇴
//    @Transactional
//    public void deleteUser(AuthUser authUser, DeleteUserRequestDto deleteReqestDto) {
//        Long id = authUser.getUserId();
//        User user = userCheckService.findUser(id);
//        //비밀번호 체크
//        String pw= deleteReqestDto.getPw();
//        checkPw(pw, user.getPw(), user.getId());
//        //유저 비활성화 코드
//        user.delete();
//    }
//
//    //회원 수정
//    @Transactional
//    public void updateUser(AuthUser authUser, PatchUserRequestDto requestDto){
//        Long id = authUser.getUserId();
//        User user = userCheckService.findUser(id);
//
//        String pw= requestDto.getPw();
//        checkPw(pw, user.getPw(), user.getId());
//
//        if(!encode.passwordVerification(requestDto.getPw())){
//            throw new IllegalArgumentException("올바르지 않은 비밀번호 형식입니다.");
//        }
//
//        if(requestDto.getPw().equals(requestDto.getNewPw())){
//            throw new SamePasswordException(ErrorCode.SAME_PASSWORD);
//        }
//        //중복된 핸드폰번호 일 경우 예외
//        userCheckService.checkHp(requestDto.getPhoneNumber());
//
//        String encodePw = encode.encode(requestDto.getNewPw());
//        user.update(encodePw,requestDto);
//    }
//
//    //계정 잠금 해제
//    @Transactional
//    public void recoveryUser(PostUserRecoveryRequestDto requestDto) {
//        //이메일에 맞는 유저 찾기
//        User user = userCheckService.findByEmailUser(requestDto.getEmail());
//        //유저의 핸드폰번호와 입력 받은 핸드폰번호가 동일한지 체크
//        if (!user.getPhoneNumber().equals(requestDto.getPhoneNumber())) {
//            throw new UnSamePhoneNumberException(ErrorCode.UNSAME_INFORMATION);
//        }
//        //동일하면 protect값 바꿔주기
//        user.changeProtect();
//    }
//    //pw가 틀렸을때 예외처리
//    @Transactional(noRollbackFor = MismatchPasswordException.class)
//    public void checkPw(String userPw, String enPw,Long userId) {
//        User user = userCheckService.findUser(userId);
//        if (!encode.matches(userPw, enPw)) {
//            user.missMatchByIncrementState();
//            throw new MismatchPasswordException(ErrorCode.MISMATCH_PASSWORD_ERROR);
//        }
//    }
//}