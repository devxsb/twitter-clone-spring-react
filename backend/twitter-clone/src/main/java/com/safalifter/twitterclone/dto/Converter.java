package com.safalifter.twitterclone.dto;

import com.safalifter.twitterclone.model.User;
import org.springframework.stereotype.Component;

@Component
public class Converter {
    public UserDto userConvertToUserDto(User from) {
        return UserDto.builder()
                .name(from.getName())
                .email(from.getEmail())
                .username(from.getUsername())
                .dateOfBirth(from.getDateOfBirth()).build();
    }
}
