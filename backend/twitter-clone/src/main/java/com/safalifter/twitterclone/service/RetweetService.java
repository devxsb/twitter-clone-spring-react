package com.safalifter.twitterclone.service;

import com.safalifter.twitterclone.dto.RetweetDto;
import com.safalifter.twitterclone.exc.NotFoundException;
import com.safalifter.twitterclone.model.Retweet;
import com.safalifter.twitterclone.model.Tweet;
import com.safalifter.twitterclone.model.User;
import com.safalifter.twitterclone.repository.RetweetRepository;
import com.safalifter.twitterclone.request.RetweetCreateRequest;
import com.safalifter.twitterclone.request.UpdateRetweetRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetweetService {
    private final RetweetRepository retweetRepository;
    private final UserService userService;
    private final TweetService tweetService;
    private final ModelMapper modelMapper;

    public RetweetDto create(RetweetCreateRequest request) {
        User user = userService.findUserById(request.getUserId());
        Tweet tweet = tweetService.findTweetById(request.getTweetId());
        Retweet retweet = Retweet.builder()
                .text(request.getText())
                .user(user)
                .tweet(tweet).build();
        return modelMapper.map(retweetRepository.save(retweet), RetweetDto.class);
    }

    public RetweetDto getRetweetById(Long id) {
        return modelMapper.map(findRetweetById(id), RetweetDto.class);
    }

    public RetweetDto updateRetweetById(Long id, UpdateRetweetRequest request) {
        Retweet inDB = findRetweetById(id);
        inDB.setText(request.getText());
        return modelMapper.map(retweetRepository.save(inDB), RetweetDto.class);
    }

    public void deleteRetweetById(Long id) {
        Retweet inDB = findRetweetById(id);
        retweetRepository.delete(inDB);
    }

    protected Retweet findRetweetById(Long id) {
        return retweetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Retweet not found!"));
    }

    public Page<RetweetDto> getTweetsRetweetsByTweetId(Long id, Pageable page) {
        Tweet inDB = tweetService.findTweetById(id);
        return retweetRepository.findRetweetsByTweet_Id(inDB.getId(), page)
                .map(x -> modelMapper.map(x, RetweetDto.class));
    }
}