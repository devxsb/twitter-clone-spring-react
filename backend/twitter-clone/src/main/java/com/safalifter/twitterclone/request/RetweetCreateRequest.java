package com.safalifter.twitterclone.request;

import lombok.Getter;

@Getter
public class RetweetCreateRequest {
    private String text;
    private Long userId;
    private Long tweetId;
}
