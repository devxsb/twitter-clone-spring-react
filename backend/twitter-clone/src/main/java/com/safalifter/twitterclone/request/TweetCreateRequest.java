package com.safalifter.twitterclone.request;

import lombok.Getter;

@Getter
public class TweetCreateRequest {
    private String text;
    private Long userId;
}