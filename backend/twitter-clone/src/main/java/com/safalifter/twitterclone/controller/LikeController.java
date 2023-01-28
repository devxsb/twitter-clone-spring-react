package com.safalifter.twitterclone.controller;

import com.safalifter.twitterclone.dto.LikeDto;
import com.safalifter.twitterclone.request.LikeCreateRequest;
import com.safalifter.twitterclone.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    ResponseEntity<LikeDto> create(@RequestBody LikeCreateRequest request) {
        return ResponseEntity.status(201).body(likeService.create(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLikeById(@PathVariable Long id) {
        likeService.deleteLikeById(id);
        return ResponseEntity.ok().build();
    }
}