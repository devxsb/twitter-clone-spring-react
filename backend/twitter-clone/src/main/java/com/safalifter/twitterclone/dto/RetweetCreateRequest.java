package com.safalifter.twitterclone.dto;

import lombok.Getter;

@Getter
public class RetweetCreateRequest {
    private String text;
    private Long userId;
    private Long tweetId;
}
