package com.safalifter.twitterclone.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long id;
    private String text;
    private Long userId;
    private Long tweetId;
    private String name;
    private String username;
    private LocalDateTime creationTimestamp;
}