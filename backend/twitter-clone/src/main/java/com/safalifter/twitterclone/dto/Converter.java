package com.safalifter.twitterclone.dto;

import com.safalifter.twitterclone.model.Like;
import com.safalifter.twitterclone.model.Tweet;
import com.safalifter.twitterclone.model.User;
import org.springframework.stereotype.Component;

@Component
public class Converter {
    public UserDto userConvertToUserDto(User from) {
        return UserDto.builder()
                .name(from.getName())
                .email(from.getEmail())
                .username(from.getUsername())
                .birthday(from.getBirthday()).build();
    }

    public TweetDto tweetConvertToTweetDto(Tweet from) {
        return TweetDto.builder()
                .id(from.getId())
                .text(from.getText())
                .userId(from.getUser().getId()).build();
    }

    public LikeDto likeConvertToLikeDto(Like from) {
        return LikeDto.builder()
                .id(from.getId())
                .userId(from.getUser().getId())
                .tweetId(from.getTweet().getId()).build();
    }
}
