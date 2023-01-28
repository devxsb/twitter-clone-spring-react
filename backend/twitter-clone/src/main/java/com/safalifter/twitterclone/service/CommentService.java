package com.safalifter.twitterclone.service;

import com.safalifter.twitterclone.dto.CommentDto;
import com.safalifter.twitterclone.exc.NotFoundException;
import com.safalifter.twitterclone.model.Comment;
import com.safalifter.twitterclone.model.Tweet;
import com.safalifter.twitterclone.model.User;
import com.safalifter.twitterclone.repository.CommentRepository;
import com.safalifter.twitterclone.request.CommentCreateRequest;
import com.safalifter.twitterclone.request.UpdateCommentRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final TweetService tweetService;
    private final ModelMapper modelMapper;

    public CommentDto create(CommentCreateRequest request) {
        User user = userService.findUserById(request.getUserId());
        Tweet tweet = tweetService.findTweetById(request.getTweetId());
        Comment comment = Comment.builder()
                .text(request.getText())
                .user(user)
                .tweet(tweet).build();
        return modelMapper.map(commentRepository.save(comment), CommentDto.class);
    }

    public CommentDto getCommentById(Long id) {
        return modelMapper.map(findCommentById(id), CommentDto.class);
    }

    public CommentDto updateCommentById(Long id, UpdateCommentRequest request) {
        Comment inDB = findCommentById(id);
        inDB.setText(request.getText());
        return modelMapper.map(commentRepository.save(inDB), CommentDto.class);
    }

    public void deleteCommentById(Long id) {
        Comment inDB = findCommentById(id);
        commentRepository.delete(inDB);
    }

    protected Comment findCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found!"));
    }

    public Page<CommentDto> getTweetsCommentsByTweetId(Long id, Pageable page) {
        Tweet inDB = tweetService.findTweetById(id);
        return commentRepository.findCommentsByTweet_Id(inDB.getId(), page)
                .map(x -> modelMapper.map(x, CommentDto.class));
    }
}