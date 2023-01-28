package com.safalifter.twitterclone.controller;

import com.safalifter.twitterclone.dto.CommentDto;
import com.safalifter.twitterclone.dto.LikeDto;
import com.safalifter.twitterclone.dto.RetweetDto;
import com.safalifter.twitterclone.dto.TweetDto;
import com.safalifter.twitterclone.request.TweetCreateRequest;
import com.safalifter.twitterclone.request.UpdateTweetRequest;
import com.safalifter.twitterclone.service.CommentService;
import com.safalifter.twitterclone.service.LikeService;
import com.safalifter.twitterclone.service.RetweetService;
import com.safalifter.twitterclone.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<Page<TweetDto>> getTweets(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.status(200).body(tweetService.getTweets(page));
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
    ResponseEntity<Page<LikeDto>> getTweetsLikesByTweetId(@PathVariable Long id,
                                                          @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.status(200).body(likeService.getTweetsLikesByTweetId(id, page));
    }

    @GetMapping("/{id}/comments")
    ResponseEntity<Page<CommentDto>> getTweetsCommentsByTweetId(@PathVariable Long id,
                                                                @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.status(200).body(commentService.getTweetsCommentsByTweetId(id, page));
    }

    @GetMapping("/{id}/retweets")
    ResponseEntity<Page<RetweetDto>> getTweetsRetweetsByTweetId(@PathVariable Long id,
                                                                @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.status(200).body(retweetService.getTweetsRetweetsByTweetId(id, page));
    }
}