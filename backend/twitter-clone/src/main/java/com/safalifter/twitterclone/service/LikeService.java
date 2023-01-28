package com.safalifter.twitterclone.service;

import com.safalifter.twitterclone.dto.LikeDto;
import com.safalifter.twitterclone.exc.NotFoundException;
import com.safalifter.twitterclone.model.Like;
import com.safalifter.twitterclone.model.Tweet;
import com.safalifter.twitterclone.model.User;
import com.safalifter.twitterclone.repository.LikeRepository;
import com.safalifter.twitterclone.request.LikeCreateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserService userService;
    private final TweetService tweetService;
    private final ModelMapper modelMapper;

    // return null to disallow the creation of multiple likes
    public LikeDto create(LikeCreateRequest request) {
        User user = userService.findUserById(request.getUserId());
        Tweet tweet = tweetService.findTweetById(request.getTweetId());
        Like like = Like.builder()
                .tweet(tweet)
                .user(user).build();
        if (!checkUserLikedForThisTweet(request.getUserId(), request.getTweetId()))
            return modelMapper.map(likeRepository.save(like), LikeDto.class);
        return null;
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

    public Page<LikeDto> getTweetsLikesByTweetId(Long id, Pageable page) {
        Tweet inDB = tweetService.findTweetById(id);
        return likeRepository.findLikesByTweet_Id(inDB.getId(), page)
                .map(x -> modelMapper.map(x, LikeDto.class));
    }

    public Page<LikeDto> getUsersLikesByUserId(Long id, Pageable page) {
        User inDB = userService.findUserById(id);
        return likeRepository.findLikesByUser_Id(inDB.getId(), page)
                .map(x -> modelMapper.map(x, LikeDto.class));
    }
}