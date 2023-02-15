package com.safalifter.twitterclone.service;

import com.safalifter.twitterclone.dto.TokenDTO;
import com.safalifter.twitterclone.dto.UserDto;
import com.safalifter.twitterclone.exc.WrongCredentialsException;
import com.safalifter.twitterclone.request.AuthRequest;
import com.safalifter.twitterclone.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;
    private final ModelMapper modelMapper;

    public TokenDTO login(AuthRequest request) {
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            return TokenDTO
                    .builder()
                    .accessToken(tokenService.generateToken(auth))
                    .userId(userService.findUserByUsername(request.getUsername()).getId())
                    .username(request.getUsername())
                    .build();
        } catch (final BadCredentialsException badCredentialsException) {
            throw new WrongCredentialsException("Invalid Username or Password");
        }
    }

    public UserDto signup(RegisterRequest request) {
        return modelMapper.map(userService.create(request), UserDto.class);
    }
}
