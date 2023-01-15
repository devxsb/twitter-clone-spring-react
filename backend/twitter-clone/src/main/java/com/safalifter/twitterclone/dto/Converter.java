package com.safalifter.twitterclone.dto;

import com.safalifter.twitterclone.model.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Converter {
    public UserDto userConvertToUserDto(User from) {
        return UserDto.builder()
                .name(from.getName())
                .email(from.getEmail())
                .username(from.getUsername())
                .birthday(from.getBirthday())
                .tweets(from.getTweets().stream()
                        .map(this::tweetConvertToTweetDto).collect(Collectors.toList()))
                .likes(from.getLikes().stream().map(this::likeConvertToLikeDto)
                        .collect(Collectors.toList())).build();
    }

    public TweetDto tweetConvertToTweetDto(Tweet from) {
        return TweetDto.builder()
                .id(from.getId())
                .text(from.getText())
                .userId(from.getUser().getId())
                .likes(from.getLikes().stream()
                        .map(this::likeConvertToLikeDto).collect(Collectors.toList()))
                .comments(from.getComments().stream().map(this::commentConvertToCommentDto)
                        .collect(Collectors.toList())).build();
    }

    public LikeDto likeConvertToLikeDto(Like from) {
        return LikeDto.builder()
                .id(from.getId())
                .userId(from.getUser().getId())
                .tweetId(from.getTweet().getId()).build();
    }

    public CommentDto commentConvertToCommentDto(Comment from) {
        return CommentDto.builder()
                .id(from.getId())
                .text(from.getText())
                .userId(from.getUser().getId())
                .tweetId(from.getTweet().getId()).build();
    }

    public RetweetDto retweetConvertToRetweetDto(Retweet from) {
        return RetweetDto.builder()
                .id(from.getId())
                .text(from.getText())
                .userId(from.getUser().getId())
                .tweetId(from.getTweet().getId()).build();
    }
}
