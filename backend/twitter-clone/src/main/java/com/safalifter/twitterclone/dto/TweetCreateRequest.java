package com.safalifter.twitterclone.dto;

import lombok.Getter;

@Getter
public class TweetCreateRequest {
    private String text;
    private Long userId;
}