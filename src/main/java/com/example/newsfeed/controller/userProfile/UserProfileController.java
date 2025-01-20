package com.example.newsfeed.controller.userProfile;

import com.example.newsfeed.dto.userLogin.request.UserDeleteRequestDto;
import com.example.newsfeed.dto.userProfile.request.UpdatePasswordRequestDto;
import com.example.newsfeed.dto.userProfile.request.UserUpdateRequestDto;
import com.example.newsfeed.dto.userProfile.response.GetAllProfileResponseDto;
import com.example.newsfeed.dto.userProfile.response.GetUserProfileResponseDto;
import com.example.newsfeed.dto.userProfile.response.UserUpdateResponseDto;
import com.example.newsfeed.service.userProfile.UserProfileService;
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

    @GetMapping("/{userName}")
    public ResponseEntity<GetUserProfileResponseDto> getUserProfileAPI(@PathVariable String userName) {
        GetUserProfileResponseDto getUserProfile = userProfileService.getUserProfile(userName);
        return new ResponseEntity<>(getUserProfile, HttpStatus.OK);
    }

    @PatchMapping("/update/{userName}")
    public ResponseEntity<UserUpdateResponseDto> updateProfileAPI(
            @PathVariable String userName,
            @Validated @RequestBody UserUpdateRequestDto requestDto,
            HttpSession session
    ) {
        UserUpdateResponseDto updateUser = userProfileService.updateProfile(userName, requestDto, session);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @PatchMapping("/password/{userName}")
    public ResponseEntity<String> updatePasswordAPI(
            @PathVariable String userName,
            @Validated @RequestBody UpdatePasswordRequestDto requestDto,
            HttpSession session
    ) {
        userProfileService.updatePassword(userName, requestDto, session);
        return new ResponseEntity<>("비밀변호가 변경되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userName}")
    public ResponseEntity<String> deleteUserAPI(
            @PathVariable String userName,
            @RequestBody UserDeleteRequestDto requestDto
    ) {
        userProfileService.deleteUser(userName, requestDto);

        return new ResponseEntity<>("회원탈퇴 성공.", HttpStatus.NO_CONTENT);
    }
}
