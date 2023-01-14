package com.safalifter.twitterclone.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;


@Builder
@Getter
public class UserDto {
    private String name;
    private String email;
    private String username;
    private Date dateOfBirth;
}
