package com.safalifter.twitterclone.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class UserDto {
    private long id;
    private String name;
    private String email;
    private String username;
    private LocalDate birthday;
    private List<TweetDto> tweets;
}
