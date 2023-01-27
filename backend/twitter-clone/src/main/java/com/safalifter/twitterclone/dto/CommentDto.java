package com.safalifter.twitterclone.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String text;
    private Long userId;
    private Long tweetId;
}