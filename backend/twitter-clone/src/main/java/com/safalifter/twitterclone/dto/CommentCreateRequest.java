package com.safalifter.twitterclone.dto;

import lombok.Getter;

@Getter
public class CommentCreateRequest {
    private String text;
    private Long userId;
    private Long tweetId;
}