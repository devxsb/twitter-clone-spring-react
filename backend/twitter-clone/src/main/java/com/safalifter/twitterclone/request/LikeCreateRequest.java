package com.safalifter.twitterclone.request;

import lombok.Getter;

@Getter
public class LikeCreateRequest {
    private long userId;
    private long tweetId;
}