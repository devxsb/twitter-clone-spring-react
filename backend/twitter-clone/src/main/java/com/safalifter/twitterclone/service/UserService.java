package com.safalifter.twitterclone.service;

import com.safalifter.twitterclone.dto.Converter;
import com.safalifter.twitterclone.dto.UserCreateRequest;
import com.safalifter.twitterclone.dto.UserDto;
import com.safalifter.twitterclone.model.User;
import com.safalifter.twitterclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Converter converter;

    public UserDto create(UserCreateRequest from) {
        User user = User.builder()
                .name(from.getName())
                .email(from.getEmail())
                .username(from.getUsername())
                .password(from.getPassword())
                .dateOfBirth(from.getDateOfBirth()).build();
        return converter.userConvertToUserDto(userRepository.save(user));
    }
}
