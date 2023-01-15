package com.safalifter.twitterclone.service;

import com.safalifter.twitterclone.dto.Converter;
import com.safalifter.twitterclone.dto.UpdateUserRequest;
import com.safalifter.twitterclone.dto.UserCreateRequest;
import com.safalifter.twitterclone.dto.UserDto;
import com.safalifter.twitterclone.exc.NotFoundException;
import com.safalifter.twitterclone.model.User;
import com.safalifter.twitterclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(converter::userConvertToUserDto).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User inDB = findUserById(id);
        return converter.userConvertToUserDto(inDB);
    }

    public UserDto updateUserById(Long id, UpdateUserRequest request) {
        User inDB = findUserById(id);
        inDB.setName(Optional.ofNullable(request.getName()).orElse(inDB.getName()));
        inDB.setUsername(Optional.ofNullable(request.getUsername()).orElse(inDB.getUsername()));
        inDB.setEmail(Optional.of(request.getEmail()).orElse(inDB.getEmail()));
        inDB.setPassword(Optional.ofNullable(request.getPassword()).orElse(inDB.getPassword()));
        inDB.setBirthday(Optional.of(request.getBirthday()).orElse(inDB.getBirthday()));
        return converter.userConvertToUserDto(userRepository.save(inDB));
    }

    public void deleteUserById(Long id) {
        User inDB = findUserById(id);
        userRepository.delete(inDB);
    }

    protected User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
    }
}