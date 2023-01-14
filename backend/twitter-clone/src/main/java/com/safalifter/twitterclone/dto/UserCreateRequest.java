package com.safalifter.twitterclone.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserCreateRequest {
    private String name;
    private String username;
    private String email;
    private String password;
    private LocalDate birthday;
}
