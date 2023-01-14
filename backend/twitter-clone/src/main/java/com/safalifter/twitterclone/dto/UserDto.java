package com.safalifter.twitterclone.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class UserDto {
    private String name;
    private String email;
    private String username;
    private LocalDate birthday;
}
