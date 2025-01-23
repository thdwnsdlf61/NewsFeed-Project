package com.example.newsfeed.controller.userProfile;

import com.example.newsfeed.dto.userLogin.request.UserDeleteRequestDto;
import com.example.newsfeed.dto.userProfile.request.UpdatePasswordRequestDto;
import com.example.newsfeed.dto.userProfile.request.UserUpdateRequestDto;
import com.example.newsfeed.dto.userProfile.response.GetAllProfileResponseDto;
import com.example.newsfeed.dto.userProfile.response.GetUserProfileResponseDto;
import com.example.newsfeed.dto.userProfile.response.UserUpdateResponseDto;
import com.example.newsfeed.service.userProfile.UserProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<List<GetAllProfileResponseDto>> getAllProfileAPI() {
        List<GetAllProfileResponseDto> allProfile = userProfileService.getAllProfile();
        return new ResponseEntity<>(allProfile, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserProfileResponseDto> getUserProfileAPI(@PathVariable Long userId) {
        GetUserProfileResponseDto getUserProfile = userProfileService.getUserProfile(userId);
        return new ResponseEntity<>(getUserProfile, HttpStatus.OK);
    }

    @PatchMapping("/profile")
    public ResponseEntity<UserUpdateResponseDto> updateProfileAPI(
            @Validated @RequestBody UserUpdateRequestDto requestDto,
            HttpServletRequest servletRequest
            ) {
        Long userId = (Long) servletRequest.getAttribute("userId");
        UserUpdateResponseDto updateUser = userProfileService.updateProfile(userId, requestDto);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @PatchMapping("/password")
    public ResponseEntity<String> updatePasswordAPI(
            @Validated @RequestBody UpdatePasswordRequestDto requestDto,
            HttpServletRequest servletRequest
    ) {
        Long userId = (Long) servletRequest.getAttribute("userId");
        userProfileService.updatePassword(userId, requestDto);
        return new ResponseEntity<>("비밀변호가 변경되었습니다.", HttpStatus.OK);
    }

//    @DeleteMapping("/delete/{userId}")
//    public ResponseEntity<String> deleteUserAPI(
//            @PathVariable Long userId,
//            @RequestBody UserDeleteRequestDto requestDto,
//            HttpSession session
//    ) {
//        userProfileService.deleteUser(userId, requestDto, session);
//
//        return new ResponseEntity<>("회원탈퇴 성공.", HttpStatus.NO_CONTENT);
//    }
}
