package com.safalifter.twitterclone.service;

import com.safalifter.twitterclone.dto.Converter;
import com.safalifter.twitterclone.dto.RetweetCreateRequest;
import com.safalifter.twitterclone.dto.RetweetDto;
import com.safalifter.twitterclone.dto.UpdateRetweetRequest;
import com.safalifter.twitterclone.exc.NotFoundException;
import com.safalifter.twitterclone.model.Retweet;
import com.safalifter.twitterclone.model.Tweet;
import com.safalifter.twitterclone.model.User;
import com.safalifter.twitterclone.repository.RetweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetweetService {
    private final RetweetRepository retweetRepository;
    private final Converter converter;
    private final UserService userService;
    private final TweetService tweetService;

    public RetweetDto create(RetweetCreateRequest request) {
        User user = userService.findUserById(request.getUserId());
        Tweet tweet = tweetService.findTweetById(request.getTweetId());
        Retweet retweet = Retweet.builder()
                .text(request.getText())
                .user(user)
                .tweet(tweet).build();
        return converter.retweetConvertToRetweetDto(retweetRepository.save(retweet));
    }

    public RetweetDto getRetweetById(Long id) {
        return converter.retweetConvertToRetweetDto(findRetweetById(id));
    }

    public RetweetDto updateRetweetById(Long id, UpdateRetweetRequest request) {
        Retweet inDB = findRetweetById(id);
        inDB.setText(request.getText());
        return converter.retweetConvertToRetweetDto(retweetRepository.save(inDB));
    }

    public void deleteRetweetById(Long id) {
        Retweet inDB = findRetweetById(id);
        retweetRepository.delete(inDB);
    }

    protected Retweet findRetweetById(Long id) {
        return retweetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Retweet not found!"));
    }

    // if commentRepository.findCommentsByTweet_Id(id) return null we can use Optional.ofNullable() but this method's returning empty
    public List<RetweetDto> getTweetsRetweetsByTweetId(Long id) {
        Tweet inDB = tweetService.findTweetById(id);
        return retweetRepository.findRetweetsByTweet_Id(inDB.getId())
                .stream().map(converter::retweetConvertToRetweetDto)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Optional::of))
                .filter(t -> !t.isEmpty()).orElseThrow(() -> new NotFoundException("Tweet hasn't retweet!"));
    }
}