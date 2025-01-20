package com.example.newsfeed.dto.userProfile.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdatePasswordRequestDto(
        String oldPassword,
        @NotBlank
        @Size(min = 8, max = 15, message = "최소 8자 이상 15자 이하로 구성해주세요.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$",
                message = "비밀번호는 대소문자,숫자를 1개 이상 포함되어야 합니다.")
        String newPassword
) {
}
