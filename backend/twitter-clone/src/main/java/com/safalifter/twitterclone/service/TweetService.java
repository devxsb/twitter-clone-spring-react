package com.safalifter.twitterclone.service;

import com.safalifter.twitterclone.dto.TweetDto;
import com.safalifter.twitterclone.exc.NotFoundException;
import com.safalifter.twitterclone.model.Tweet;
import com.safalifter.twitterclone.model.User;
import com.safalifter.twitterclone.repository.TweetRepository;
import com.safalifter.twitterclone.request.TweetCreateRequest;
import com.safalifter.twitterclone.request.UpdateTweetRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TweetService {
    private final TweetRepository tweetRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public TweetDto create(TweetCreateRequest request) {
        User inDB = userService.findUserById(request.getUserId());
        Tweet tweet = Tweet.builder()
                .text(request.getText())
                .user(inDB).build();
        return modelMapper.map(tweetRepository.save(tweet), TweetDto.class);
    }

    public Page<TweetDto> getTweets(Pageable page) {
        return tweetRepository.findAll(page).map(x -> modelMapper.map(x, TweetDto.class));
    }

    public TweetDto getTweetById(Long id) {
        return modelMapper.map(findTweetById(id), TweetDto.class);
    }

    public TweetDto updateTweetById(Long id, UpdateTweetRequest request) {
        Tweet inDB = findTweetById(id);
        inDB.setText(request.getText());
        return modelMapper.map(tweetRepository.save(inDB), TweetDto.class);
    }

    public void deleteTweetById(Long id) {
        Tweet inDB = findTweetById(id);
        tweetRepository.delete(inDB);
    }

    protected Tweet findTweetById(Long id) {
        return tweetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tweet not found!"));
    }

    public Page<TweetDto> getUsersTweetsByUserId(Long id, Pageable page) {
        User inDB = userService.findUserById(id);
        return tweetRepository.findAllByUser_Id(inDB.getId(), page)
                .map(x -> modelMapper.map(x, TweetDto.class));
    }
}