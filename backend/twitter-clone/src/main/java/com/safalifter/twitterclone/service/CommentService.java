package com.safalifter.twitterclone.service;

import com.safalifter.twitterclone.dto.CommentCreateRequest;
import com.safalifter.twitterclone.dto.CommentDto;
import com.safalifter.twitterclone.dto.Converter;
import com.safalifter.twitterclone.dto.UpdateCommentRequest;
import com.safalifter.twitterclone.exc.NotFoundException;
import com.safalifter.twitterclone.model.Comment;
import com.safalifter.twitterclone.model.Tweet;
import com.safalifter.twitterclone.model.User;
import com.safalifter.twitterclone.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final Converter converter;
    private final UserService userService;
    private final TweetService tweetService;

    public CommentDto create(CommentCreateRequest request) {
        User user = userService.findUserById(request.getUserId());
        Tweet tweet = tweetService.findTweetById(request.getTweetId());
        Comment comment = Comment.builder()
                .text(request.getText())
                .user(user)
                .tweet(tweet).build();
        return converter.commentConvertToCommentDto(commentRepository.save(comment));
    }

    public CommentDto getCommentById(Long id) {
        return converter.commentConvertToCommentDto(findCommentById(id));
    }

    // if commentRepository.findCommentsByTweet_Id(inDB.getId()) return null we can use Optional.ofNullable() but this method's returning empty
    public List<CommentDto> getCommentsByTweetId(Long id) {
        Tweet inDB = tweetService.findTweetById(id);
        return commentRepository.findCommentsByTweet_Id(inDB.getId())
                .stream().map(converter::commentConvertToCommentDto)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Optional::of))
                .filter(t -> !t.isEmpty()).orElseThrow(() -> new NotFoundException("Tweet hasn't comment!"));
    }

    public CommentDto updateCommentById(Long id, UpdateCommentRequest request) {
        Comment inDB = findCommentById(id);
        inDB.setText(request.getText());
        return converter.commentConvertToCommentDto(commentRepository.save(inDB));
    }

    public void deleteCommentById(Long id) {
        Comment inDB = findCommentById(id);
        commentRepository.delete(inDB);
    }

    protected Comment findCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found!"));
    }
}
