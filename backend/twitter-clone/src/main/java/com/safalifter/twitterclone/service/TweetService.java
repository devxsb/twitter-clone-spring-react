package com.safalifter.twitterclone.service;

import com.safalifter.twitterclone.request.TweetCreateRequest;
import com.safalifter.twitterclone.dto.TweetDto;
import com.safalifter.twitterclone.request.UpdateTweetRequest;
import com.safalifter.twitterclone.exc.NotFoundException;
import com.safalifter.twitterclone.model.Tweet;
import com.safalifter.twitterclone.model.User;
import com.safalifter.twitterclone.repository.TweetRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<TweetDto> getTweets() {
        return tweetRepository.findAll().stream()
                .map(x -> modelMapper.map(x, TweetDto.class)).collect(Collectors.toList());
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

    // if tweetRepository.findAllByUser_Id(inDB.getId()) return null we can use Optional.ofNullable() but this method's returning empty
    public List<TweetDto> getUsersTweetsByUserId(Long id) {
        User inDB = userService.findUserById(id);
        return tweetRepository.findAllByUser_Id(inDB.getId())
                .stream().map(x -> modelMapper.map(x, TweetDto.class))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Optional::of))
                .filter(t -> !t.isEmpty()).orElseThrow(() -> new NotFoundException("User hasn't tweet!"));
    }
}