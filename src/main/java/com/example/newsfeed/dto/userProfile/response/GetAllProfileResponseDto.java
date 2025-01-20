package com.example.newsfeed.dto.userProfile.response;

import com.example.newsfeed.common.entity.user.User;

public record GetAllProfileResponseDto(
        Long userId,
        String userName,
        String nickName
) {
    public static GetAllProfileResponseDto getAllProfileDto(User user) {
        return new GetAllProfileResponseDto(
                user.getUserId(),
                user.getUserName(),
                user.getNickName()
        );
    }
}
