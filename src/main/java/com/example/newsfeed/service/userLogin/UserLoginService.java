package com.example.newsfeed.service.userLogin;

import com.example.newsfeed.common.config.JwtTokenProvider;
import com.example.newsfeed.common.config.PasswordEncoder;
import com.example.newsfeed.common.entity.user.User;
import com.example.newsfeed.dto.userLogin.request.UserLoginRequestDto;
import com.example.newsfeed.dto.userLogin.request.UserSignUpRequestDto;
import com.example.newsfeed.dto.userLogin.response.UserLoginResponseDto;
import com.example.newsfeed.dto.userLogin.response.UserSignUpResponseDto;
import com.example.newsfeed.repository.user.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public UserSignUpResponseDto userSignUp(UserSignUpRequestDto requestDto) {

        if (userRespository.findByEmail(requestDto.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "사용중인 이메일입니다.");
        }

        String encodePassword = passwordEncoder.encode(requestDto.password());
//        if (userRespository.existsByEmail(requestDto.email())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "사용중인 이메일입니다.");
//        }
        User user = new User();
        user.signUpUser(requestDto.userName(), requestDto.email(), encodePassword);
        User savedUser = userRespository.save(user);

        return UserSignUpResponseDto.signUpDto(savedUser);
    }

    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {

        User foundUser = userRespository.findByEmail(requestDto.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "가입된 계정이 아닙니다."));

        if (!passwordEncoder.matches(requestDto.password(), foundUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        String loginToken = tokenProvider.createToken(foundUser.getUserId(), foundUser.getEmail());

        return UserLoginResponseDto.loginDto(loginToken, foundUser);
    }

//    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
//        User foundUser = userRespository.findByEmail(requestDto.email())
//                .orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED, "가입된 계정이 아닙니다."));
//
//        if (!passwordEncoder.matches(requestDto.password(), foundUser.getPassword())) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
//        }
//        return UserLoginResponseDto.loginDto(foundUser);
//    }
}
