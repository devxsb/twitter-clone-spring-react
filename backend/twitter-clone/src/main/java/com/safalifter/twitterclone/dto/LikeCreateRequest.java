package com.safalifter.twitterclone.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LikeCreateRequest {
    @NotBlank
    private long userId;
    @NotBlank
    private long tweetId;
}