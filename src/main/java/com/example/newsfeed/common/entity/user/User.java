package com.example.newsfeed.common.entity.user;

import com.example.newsfeed.common.entity.BaseEntity;
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
}
