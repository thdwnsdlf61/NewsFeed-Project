package com.example.newsfeed.service.userProfile;

import com.example.newsfeed.common.config.Const;
import com.example.newsfeed.common.config.PasswordEncoder;
import com.example.newsfeed.common.entity.user.User;
import com.example.newsfeed.dto.userLogin.request.UserDeleteRequestDto;
import com.example.newsfeed.dto.userLogin.response.UserLoginResponseDto;
import com.example.newsfeed.dto.userProfile.request.UpdatePasswordRequestDto;
import com.example.newsfeed.dto.userProfile.request.UserUpdateRequestDto;
import com.example.newsfeed.dto.userProfile.response.GetAllProfileResponseDto;
import com.example.newsfeed.dto.userProfile.response.GetUserProfileResponseDto;
import com.example.newsfeed.dto.userProfile.response.UserUpdateResponseDto;
import com.example.newsfeed.repository.user.UserRespository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;

    public List<GetAllProfileResponseDto> getAllProfile() {
        List<GetAllProfileResponseDto> profileList = userRespository.findAll()
                .stream()
                .map(GetAllProfileResponseDto::getAllProfileDto)
                .toList();
        return profileList;
    }

    public GetUserProfileResponseDto getUserProfile(String userName) {
        User foundUser = findUser(userName);

        return GetUserProfileResponseDto.getProfileDto(foundUser);
    }

    public UserUpdateResponseDto updateProfile(String userName, UserUpdateRequestDto requestDto, HttpSession session) {
        User foundUser = findUser(userName);
        checkAuthorize(session, foundUser);
        foundUser.updateUser(requestDto);
        User updatedUser = userRespository.save(foundUser);
        return UserUpdateResponseDto.updateDto(updatedUser);
    }

    public void updatePassword(String userName, UpdatePasswordRequestDto requestDto, HttpSession session) {
        User foundUser = findUser(userName);
        checkAuthorize(session, foundUser);

        if (!passwordEncoder.matches(requestDto.oldPassword(), foundUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }
        foundUser.updatePassword(requestDto.newPassword());

    }

    public void deleteUser(String userName, UserDeleteRequestDto requestDto) {
        User foundUser = findUser(userName);
        if (!passwordEncoder.matches(foundUser.getPassword(), requestDto.pasword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }

    }

    private User findUser(String userName) {
        return userRespository.findUserByUserName(userName)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저가 없습니다."));
    }

    private static void checkAuthorize(HttpSession session, User foundUser) {
        UserLoginResponseDto loginUser = (UserLoginResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (!loginUser.email().equals(foundUser.getEmail())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"권한이 없습니다.");
        }
    }
}
