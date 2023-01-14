package com.safalifter.twitterclone.controller;

import com.safalifter.twitterclone.dto.UserCreateRequest;
import com.safalifter.twitterclone.dto.UserDto;
import com.safalifter.twitterclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    ResponseEntity<UserDto> create(@RequestBody UserCreateRequest user) {
        return ResponseEntity.status(201).body(userService.create(user));
    }
}
