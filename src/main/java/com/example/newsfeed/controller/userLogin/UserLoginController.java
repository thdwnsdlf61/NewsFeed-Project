package com.example.newsfeed.controller.userLogin;

import com.example.newsfeed.common.config.Const;
import com.example.newsfeed.dto.userLogin.request.UserLoginRequestDto;
import com.example.newsfeed.dto.userLogin.request.UserSignUpRequestDto;
import com.example.newsfeed.dto.userLogin.response.UserLoginResponseDto;
import com.example.newsfeed.dto.userLogin.response.UserSignUpResponseDto;
import com.example.newsfeed.service.userLogin.UserLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserLoginController {
    private final UserLoginService userLoginService;

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDto> userSignUpAPI(
            @Validated @RequestBody UserSignUpRequestDto requestDto) {

        UserSignUpResponseDto userSignUpResponseDto = userLoginService.userSignUp(requestDto);

        return new ResponseEntity<>(userSignUpResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> userLoginAPI(
            @Validated @RequestBody UserLoginRequestDto requestDto,
            HttpServletRequest httpRequest
    ) {
        UserLoginResponseDto loginUser = userLoginService.login(requestDto);

        HttpSession session = httpRequest.getSession();
        session.setAttribute(Const.LOGIN_USER, loginUser);

        return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> logoutAPI(HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>("로그아웃되었습니다.", HttpStatus.NO_CONTENT);
    }
}
