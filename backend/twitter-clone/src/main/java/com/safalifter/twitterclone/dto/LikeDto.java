package com.safalifter.twitterclone.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LikeDto {
    private long id;
    private long userId;
    private long tweetId;
}