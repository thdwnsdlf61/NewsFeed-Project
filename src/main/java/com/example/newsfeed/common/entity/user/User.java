package com.example.newsfeed.common.entity.user;

import com.example.newsfeed.common.entity.BaseEntity;
import com.example.newsfeed.dto.userLogin.request.UserSignUpRequestDto;
import com.example.newsfeed.dto.userProfile.request.UserUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "user")
@Entity
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String email;

    private String password;

    private String nickName;

    private String birthDate;

    private String profileComment;

    public void signUpUser(String userName,String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void updateUser(UserUpdateRequestDto requestDto) {
        if (requestDto.userName() != null) {
            this.userName = requestDto.userName();
        }
        if (requestDto.email() != null) {
            this.email = requestDto.email();
        }
        if (requestDto.nickName() != null) {
            this.nickName = requestDto.nickName();
        }
        if (requestDto.birthDate() != null) {
            this.birthDate = requestDto.birthDate();
        }
        if (requestDto.profileComment() != null) {
            this.profileComment = requestDto.profileComment();
        }
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
