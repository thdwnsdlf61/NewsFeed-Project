package com.example.newsfeed.common.entity.newsFeed;

import com.example.newsfeed.common.entity.BaseEntity;
import com.example.newsfeed.common.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "newsfeed")
@Entity
@NoArgsConstructor
public class NewsFeed extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsFeedId;

    private String title;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
