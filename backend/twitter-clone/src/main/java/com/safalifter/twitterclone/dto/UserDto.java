package com.safalifter.twitterclone.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {
    private long id;
    private String name;
    private String email;
    private String username;
    private LocalDate birthday;
    private String bio;
    private String location;
    private String webSite;
    private LocalDateTime creationTimestamp;
    private List<TweetDto> tweets;
    private List<LikeDto> likes;
    private String profileImageLink;
}
