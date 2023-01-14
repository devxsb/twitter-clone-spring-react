package com.safalifter.twitterclone.service;

import com.safalifter.twitterclone.dto.Converter;
import com.safalifter.twitterclone.dto.UserCreateRequest;
import com.safalifter.twitterclone.dto.UserDto;
import com.safalifter.twitterclone.exc.NotFoundException;
import com.safalifter.twitterclone.model.User;
import com.safalifter.twitterclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Converter converter;

    public UserDto create(UserCreateRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword())
                .birthday(request.getBirthday()).build();
        return converter.userConvertToUserDto(userRepository.save(user));
    }

    protected User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
    }
}
