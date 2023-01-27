package com.safalifter.twitterclone.service;

import com.safalifter.twitterclone.dto.UpdateUserRequest;
import com.safalifter.twitterclone.dto.RegisterRequest;
import com.safalifter.twitterclone.dto.UserDto;
import com.safalifter.twitterclone.exc.NotFoundException;
import com.safalifter.twitterclone.model.Role;
import com.safalifter.twitterclone.model.User;
import com.safalifter.twitterclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto create(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .birthday(request.getBirthday())
                .role(Role.USER).build();
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(x -> modelMapper.map(x, UserDto.class)).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User inDB = findUserById(id);
        return modelMapper.map(inDB, UserDto.class);
    }

    public UserDto updateUserById(Long id, UpdateUserRequest request) {
        User inDB = findUserById(id);
        inDB.setName(Optional.ofNullable(request.getName()).orElse(inDB.getName()));
        inDB.setUsername(Optional.ofNullable(request.getUsername()).orElse(inDB.getUsername()));
        inDB.setEmail(Optional.ofNullable(request.getEmail()).orElse(inDB.getEmail()));
        inDB.setPassword(Optional.ofNullable(request.getPassword()).orElse(inDB.getPassword()));
        inDB.setBirthday(Optional.ofNullable(request.getBirthday()).orElse(inDB.getBirthday()));
        return modelMapper.map(userRepository.save(inDB), UserDto.class);
    }

    public void deleteUserById(Long id) {
        User inDB = findUserById(id);
        userRepository.delete(inDB);
    }

    protected User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
    }

    protected User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found!"));
    }
}