package com.safalifter.twitterclone.repository;

import com.safalifter.twitterclone.model.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {
    List<Retweet> findRetweetsByTweet_Id(Long id);
}
