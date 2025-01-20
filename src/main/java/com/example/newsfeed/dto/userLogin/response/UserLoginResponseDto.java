package com.example.newsfeed.dto.userLogin.response;

import com.example.newsfeed.common.entity.user.User;

public record UserLoginResponseDto(
        String userName,
        String email
        ) {

        public static UserLoginResponseDto loginDto(User user) {
                return new UserLoginResponseDto(
                        user.getUserName(),
                        user.getEmail()
                );
        }
}
