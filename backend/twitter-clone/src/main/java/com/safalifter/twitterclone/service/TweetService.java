package com.safalifter.twitterclone.service;

import com.safalifter.twitterclone.dto.Converter;
import com.safalifter.twitterclone.dto.TweetCreateRequest;
import com.safalifter.twitterclone.dto.TweetDto;
import com.safalifter.twitterclone.dto.UpdateTweetRequest;
import com.safalifter.twitterclone.exc.NotFoundException;
import com.safalifter.twitterclone.model.Tweet;
import com.safalifter.twitterclone.model.User;
import com.safalifter.twitterclone.repository.TweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TweetService {
    private final TweetRepository tweetRepository;
    private final Converter converter;
    private final UserService userService;

    public TweetDto create(TweetCreateRequest request) {
        User inDB = userService.findUserById(request.getUserId());
        Tweet tweet = Tweet.builder()
                .text(request.getText())
                .user(inDB).build();
        return converter.tweetConvertToTweetDto(tweetRepository.save(tweet));
    }

    public List<TweetDto> getTweets() {
        return tweetRepository.findAll().stream()
                .map(converter::tweetConvertToTweetDto).collect(Collectors.toList());
    }

    public TweetDto getTweetById(Long id) {
        return converter.tweetConvertToTweetDto(findTweetById(id));
    }

    public TweetDto updateTweetById(Long id, UpdateTweetRequest request) {
        Tweet inDB = findTweetById(id);
        inDB.setText(request.getText());
        return converter.tweetConvertToTweetDto(tweetRepository.save(inDB));
    }

    public void deleteTweetById(Long id) {
        Tweet inDB = findTweetById(id);
        tweetRepository.delete(inDB);
    }

    protected Tweet findTweetById(Long id) {
        return tweetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tweet not found!"));
    }

    // if tweetRepository.findAllByUser_Id(inDB.getId()) return null we can use Optional.ofNullable() but this method's returning empty
    public List<TweetDto> getUsersTweetsByUserId(Long id) {
        User inDB = userService.findUserById(id);
        return tweetRepository.findAllByUser_Id(inDB.getId())
                .stream().map(converter::tweetConvertToTweetDto)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Optional::of))
                .filter(t -> !t.isEmpty()).orElseThrow(() -> new NotFoundException("User hasn't tweet!"));
    }
}