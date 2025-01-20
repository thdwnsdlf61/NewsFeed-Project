package com.example.newsfeed.dto.userProfile.response;

import com.example.newsfeed.common.entity.user.User;

import java.time.LocalDateTime;

public record GetUserProfileResponseDto(
        Long userId,
        String userName,
        String email,
        String nickName,
        String birthDate,
        String profileComment,
        LocalDateTime createdDate
) {
    public static GetUserProfileResponseDto getProfileDto(User user) {
        return new GetUserProfileResponseDto(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getNickName(),
                user.getBirthDate(),
                user.getProfileComment(),
                user.getCreatedDate()
        );
    }
}
