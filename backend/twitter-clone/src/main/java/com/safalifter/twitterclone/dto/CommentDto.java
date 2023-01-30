package com.safalifter.twitterclone.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentDto {
    private Long id;
    private String text;
    private Long userId;
    private Long tweetId;
    private String name;
    private String username;
    private LocalDateTime creationTimestamp;
    private List<LikeDto> likes;
    private List<RetweetDto> retweets;
}