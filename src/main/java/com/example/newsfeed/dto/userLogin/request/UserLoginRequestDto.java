package com.example.newsfeed.dto.userLogin.request;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDto(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
