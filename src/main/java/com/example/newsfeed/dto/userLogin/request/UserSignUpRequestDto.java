package com.example.newsfeed.dto.userLogin.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserSignUpRequestDto(
        @NotBlank(message = "이름은 필수값입니다.")
        String userName,
        @NotBlank(message = "이메일은 필수값입니다.")
        @Email(message = "올바른 이메일 형식으로 입력해주세요.")
        String email,
        @NotBlank
        @Size(min = 8, max = 15, message = "최소 8자 이상 15자 이하로 구성해주세요.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$",
                message = "비밀번호는 대소문자,숫자를 1개 이상 포함되어야 합니다.")
        String password
) {
}
