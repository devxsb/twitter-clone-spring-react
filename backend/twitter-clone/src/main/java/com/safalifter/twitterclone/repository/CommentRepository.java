package com.safalifter.twitterclone.repository;

import com.safalifter.twitterclone.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByTweet_Id(Long id);
}
