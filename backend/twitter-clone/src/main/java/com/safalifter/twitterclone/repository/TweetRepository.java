package com.safalifter.twitterclone.repository;

import com.safalifter.twitterclone.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findAllByUser_Id(Long id);
}