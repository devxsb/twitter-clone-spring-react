package com.safalifter.twitterclone.dto;

import lombok.Getter;

@Getter
public class LikeCreateRequest {
    private long userId;
    private long tweetId;
}