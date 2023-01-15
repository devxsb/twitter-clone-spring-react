package com.safalifter.twitterclone.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TweetCreateRequest {
    private String text;
    @NotBlank
    private Long userId;
}