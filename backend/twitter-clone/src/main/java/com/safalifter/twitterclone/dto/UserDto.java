package com.safalifter.twitterclone.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {
    private long id;
    private String name;
    private String email;
    private String username;
    private LocalDate birthday;
    private List<TweetDto> tweets;
    private List<LikeDto> likes;
}
