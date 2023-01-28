package com.safalifter.twitterclone.controller;

import com.safalifter.twitterclone.dto.TokenDTO;
import com.safalifter.twitterclone.dto.UserDto;
import com.safalifter.twitterclone.request.AuthRequest;
import com.safalifter.twitterclone.request.RegisterRequest;
import com.safalifter.twitterclone.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    ResponseEntity<TokenDTO> handleLogin(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/signup")
    ResponseEntity<UserDto> handleSignUp(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }
}
