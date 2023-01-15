package com.safalifter.twitterclone.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RetweetDto {
    private Long id;
    private String text;
    private Long userId;
    private Long tweetId;
}
