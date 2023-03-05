package com.safalifter.twitterclone.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TweetDto {
    private Long id;
    private String text;
    private Long userId;
    private String name;
    private String username;
    private String userProfileImageLink;
    private LocalDateTime creationTimestamp;
    private List<LikeDto> likes;
    private List<CommentDto> comments;
    private List<RetweetDto> retweets;
}