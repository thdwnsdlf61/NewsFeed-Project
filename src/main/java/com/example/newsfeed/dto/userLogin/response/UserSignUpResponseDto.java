package com.example.newsfeed.dto.userLogin.response;

import com.example.newsfeed.common.entity.user.User;

public record UserSignUpResponseDto(
        String userName,
        String email
) {
    public static UserSignUpResponseDto signUpDto(User user) {
        return new UserSignUpResponseDto (
                user.getUserName(),
                user.getEmail()
        );
    }
}
