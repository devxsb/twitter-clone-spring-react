package com.safalifter.twitterclone.service;

import com.safalifter.twitterclone.dto.UserDto;
import com.safalifter.twitterclone.exc.NotFoundException;
import com.safalifter.twitterclone.model.Role;
import com.safalifter.twitterclone.model.User;
import com.safalifter.twitterclone.repository.UserRepository;
import com.safalifter.twitterclone.request.RegisterRequest;
import com.safalifter.twitterclone.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Page<UserDto> getUsers(Pageable page) {
        return userRepository.findAll(page)
                .map(x -> modelMapper.map(x, UserDto.class));
    }

    public UserDto getUserById(Long id) { // will be used
        User inDB = findUserById(id);
        return modelMapper.map(inDB, UserDto.class);
    }

    public UserDto getUserByUsername(String username) {
        return modelMapper.map(findUserByUsername(username), UserDto.class);
    }

    public UserDto updateUserById(Long id, UpdateUserRequest request) { // password and username will be added
        User inDB = findUserById(id);
        inDB.setName(Optional.ofNullable(request.getName()).orElse(inDB.getName()));
        inDB.setBio(Optional.ofNullable(request.getBio()).orElse(inDB.getBio()));
        inDB.setLocation(Optional.ofNullable(request.getLocation()).orElse(inDB.getLocation()));
        inDB.setWebSite(Optional.ofNullable(request.getWebSite()).orElse(inDB.getWebSite()));
        return modelMapper.map(userRepository.save(inDB), UserDto.class);
    }

    public void deleteUserById(Long id) {
        User inDB = findUserById(id);
        userRepository.delete(inDB);
    }

    public void updateUserProfileImage(String file, String username) {
        User inDB = findUserByUsername(username);
        inDB.setProfileImageLink(file);
        userRepository.save(inDB);
    }

    protected User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
    }

    protected User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found!"));
    }
}