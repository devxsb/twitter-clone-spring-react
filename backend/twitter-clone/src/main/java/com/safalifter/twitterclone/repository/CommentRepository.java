package com.safalifter.twitterclone.repository;

import com.safalifter.twitterclone.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findCommentsByTweet_Id(Long id, Pageable page);
}
