package com.safalifter.twitterclone.service;

import com.safalifter.twitterclone.dto.Converter;
import com.safalifter.twitterclone.dto.LikeCreateRequest;
import com.safalifter.twitterclone.dto.LikeDto;
import com.safalifter.twitterclone.exc.NotFoundException;
import com.safalifter.twitterclone.model.Like;
import com.safalifter.twitterclone.model.Tweet;
import com.safalifter.twitterclone.model.User;
import com.safalifter.twitterclone.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final Converter converter;

    private final TweetService tweetService;
    private final UserService userService;

    // return null to disallow the creation of multiple likes
    public LikeDto create(LikeCreateRequest request) {
        Tweet tweet = tweetService.findTweetById(request.getTweetId());
        User user = userService.findUserById(request.getUserId());
        Like like = Like.builder()
                .tweet(tweet)
                .user(user).build();
        if (!checkUserLikedForThisTweet(request.getUserId(), request.getTweetId()))
            return converter.likeConvertToLikeDto(likeRepository.save(like));
        return null;
    }

    // findAll not created because fetching all likes service is not required
    public Object getLikes(Long userId, Long tweetId) {
        if (userId != null && tweetId == null) return getUsersLikes(userId);
        if (tweetId != null && userId == null) return getTweetLikes(tweetId);
        return checkUserLikedForThisTweet(userId, tweetId);
    }

    public List<LikeDto> getTweetLikes(Long tweetId) {
        return likeRepository.findLikesByTweet_Id(tweetId).stream()
                .map(converter::likeConvertToLikeDto).collect(Collectors.toList());
    }

    public List<LikeDto> getUsersLikes(Long userId) {
        return likeRepository.findLikesByUser_Id(userId).stream()
                .map(converter::likeConvertToLikeDto).collect(Collectors.toList());
    }

    public void deleteLikeById(Long id) {
        Like toBeDeletedLike = findLikeById(id);
        likeRepository.delete(toBeDeletedLike);
    }

    protected Like findLikeById(Long id) {
        return likeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Like not found"));
    }

    protected boolean checkUserLikedForThisTweet(Long userId, Long tweetId) {
        return likeRepository.findLikeByUser_IdAndTweet_Id(userId, tweetId).isPresent();
    }
}