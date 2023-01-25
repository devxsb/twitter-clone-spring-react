package com.safalifter.twitterclone.controller;

import com.safalifter.twitterclone.dto.*;
import com.safalifter.twitterclone.service.CommentService;
import com.safalifter.twitterclone.service.LikeService;
import com.safalifter.twitterclone.service.RetweetService;
import com.safalifter.twitterclone.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tweets")
@RequiredArgsConstructor
public class TweetController {
    private final TweetService tweetService;
    private final LikeService likeService;
    private final RetweetService retweetService;
    private final CommentService commentService;

    @PostMapping
    ResponseEntity<TweetDto> create(@RequestBody TweetCreateRequest request) {
        return ResponseEntity.status(201).body(tweetService.create(request));
    }

    @GetMapping
    ResponseEntity<List<TweetDto>> getTweets() {
        return ResponseEntity.status(200).body(tweetService.getTweets());
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

    @GetMapping("/{id}/likes")
    ResponseEntity<List<LikeDto>> getTweetsLikesByTweetId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(likeService.getTweetsLikesByTweetId(id));
    }

    @GetMapping("/{id}/comments")
    ResponseEntity<List<CommentDto>> getTweetsCommentsByTweetId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(commentService.getTweetsCommentsByTweetId(id));
    }

    @GetMapping("/{id}/retweets")
    ResponseEntity<List<RetweetDto>> getTweetsRetweetsByTweetId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(retweetService.getTweetsRetweetsByTweetId(id));
    }
}