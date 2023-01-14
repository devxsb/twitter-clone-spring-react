package com.safalifter.twitterclone.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@SuperBuilder
@RequiredArgsConstructor
@Getter
@Setter
public class Tweet extends BaseEntity {
    private String text;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private User user;
}