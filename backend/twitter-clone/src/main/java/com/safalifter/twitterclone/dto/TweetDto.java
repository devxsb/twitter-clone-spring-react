package com.safalifter.twitterclone.dto;

import lombok.Data;

import java.util.List;

@Data
public class TweetDto {
    private Long id;
    private String text;
    private Long userId;
    private List<LikeDto> likes;
    private List<CommentDto> comments;
}