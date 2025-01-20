package com.example.newsfeed.dto.userProfile.response;

import com.example.newsfeed.common.entity.user.User;

import java.time.LocalDateTime;

public record UserUpdateResponseDto(
        Long userId,
        String userName,
        String email,
        String nickName,
        String birthDate,
        String profileComment,
        LocalDateTime updatedDate
) {
    public static UserUpdateResponseDto updateDto(User user) {
        return new UserUpdateResponseDto(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getNickName(),
                user.getBirthDate(),
                user.getProfileComment(),
                user.getUpdatedDate()
        );
    }
}
