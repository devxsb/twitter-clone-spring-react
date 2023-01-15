package com.safalifter.twitterclone.controller;

import com.safalifter.twitterclone.dto.TweetCreateRequest;
import com.safalifter.twitterclone.dto.TweetDto;
import com.safalifter.twitterclone.dto.UpdateTweetRequest;
import com.safalifter.twitterclone.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/tweets")
@RequiredArgsConstructor
public class TweetController {
    private final TweetService tweetService;

    @PostMapping
    ResponseEntity<TweetDto> create(@Valid @RequestBody TweetCreateRequest request) {
        return ResponseEntity.status(201).body(tweetService.create(request));
    }

    @GetMapping
    ResponseEntity<List<TweetDto>> getTweets(@RequestParam(required = false) Long userId) {
        return ResponseEntity.status(200).body(tweetService.getTweets(userId));
    }

    @GetMapping("/{id}")
    ResponseEntity<TweetDto> getTweetById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(tweetService.getTweetById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<TweetDto> updateTweetById(@PathVariable Long id,
                                             @RequestBody UpdateTweetRequest request) {
        return ResponseEntity.status(200).body(tweetService.updateTweetById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweetById(@PathVariable Long id) {
        tweetService.deleteTweetById(id);
        return ResponseEntity.ok().build();
    }
}
