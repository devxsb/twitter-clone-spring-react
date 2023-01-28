package com.safalifter.twitterclone.controller;

import com.safalifter.twitterclone.dto.*;
import com.safalifter.twitterclone.request.UpdateUserRequest;
import com.safalifter.twitterclone.service.LikeService;
import com.safalifter.twitterclone.service.TweetService;
import com.safalifter.twitterclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TweetService tweetService;
    private final LikeService likeService;

    @GetMapping
    ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @GetMapping("{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<UserDto> updateUserById(@PathVariable Long id,
                                           @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.status(200).body(userService.updateUserById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/tweets")
    ResponseEntity<List<TweetDto>> getUsersTweetsByUserId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(tweetService.getUsersTweetsByUserId(id));
    }

    @GetMapping("/{id}/likes")
    ResponseEntity<List<LikeDto>> getUsersLikesByUserId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(likeService.getUsersLikesByUserId(id));
    }
}