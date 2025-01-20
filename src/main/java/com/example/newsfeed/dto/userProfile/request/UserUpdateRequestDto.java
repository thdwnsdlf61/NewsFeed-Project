package com.example.newsfeed.dto.userProfile.request;

import jakarta.validation.constraints.Email;

public record UserUpdateRequestDto(
        String userName,
        @Email
        String email,
        String nickName,
        String birthDate,
        String profileComment
) {
}
