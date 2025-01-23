package com.example.newsfeed.dto.userLogin.response;

import com.example.newsfeed.common.entity.user.User;

public record UserLoginResponseDto(
        String token,
        Long userId,
        String userName
        ) {

        public static UserLoginResponseDto loginDto(String bearerToken, User user) {
                return new UserLoginResponseDto(
                        bearerToken,
                        user.getUserId(),
                        user.getUserName());
        }

//        public static UserLoginResponseDto loginDto(User user) {
//                return new UserLoginResponseDto(
//                        user.getUserName(),
//                        user.getEmail()
//                );
//        }
}
