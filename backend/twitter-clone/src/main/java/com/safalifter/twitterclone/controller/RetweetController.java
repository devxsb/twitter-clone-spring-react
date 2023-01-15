package com.safalifter.twitterclone.controller;

import com.safalifter.twitterclone.dto.RetweetCreateRequest;
import com.safalifter.twitterclone.dto.RetweetDto;
import com.safalifter.twitterclone.dto.UpdateRetweetRequest;
import com.safalifter.twitterclone.service.RetweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/retweets")
@RequiredArgsConstructor
public class RetweetController {
    private final RetweetService retweetService;

    @PostMapping
    ResponseEntity<RetweetDto> create(@RequestBody RetweetCreateRequest request) {
        return ResponseEntity.status(201).body(retweetService.create(request));
    }

    @GetMapping("/{id}")
    ResponseEntity<RetweetDto> getRetweetById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(retweetService.getRetweetById(id));
    }

    @GetMapping
    ResponseEntity<List<RetweetDto>> getRetweetsByTweetId(@RequestParam(required = false) Long tweetId) {
        return ResponseEntity.status(200).body(retweetService.getRetweetsByTweetId(tweetId));
    }

    @PutMapping("/{id}")
    ResponseEntity<RetweetDto> updateRetweetById(@PathVariable Long id,
                                                 @RequestBody UpdateRetweetRequest request) {
        return ResponseEntity.status(200).body(retweetService.updateRetweetById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRetweetById(@PathVariable Long id) {
        retweetService.deleteRetweetById(id);
        return ResponseEntity.ok().build();
    }
}