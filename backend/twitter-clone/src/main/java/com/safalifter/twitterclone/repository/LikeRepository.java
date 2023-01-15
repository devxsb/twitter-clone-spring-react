package com.safalifter.twitterclone.repository;

import com.safalifter.twitterclone.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findLikesByUser_Id(Long userId);

    List<Like> findLikesByTweet_Id(Long tweetId);

    Optional<Like> findLikeByUser_IdAndTweet_Id(Long userId, Long tweetId);
}