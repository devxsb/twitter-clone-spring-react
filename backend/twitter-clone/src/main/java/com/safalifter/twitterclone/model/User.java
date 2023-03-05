package com.safalifter.twitterclone.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "users")
@SuperBuilder
@RequiredArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {
    private String name;

    @Column(unique = true)
    private String email;
    // unique problem will resolve with auth
    @Column(unique = true)
    private String username;

    private String password;

    private LocalDate birthday;

    private String bio;

    private String location;

    private String webSite;

    private String profileImageLink;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tweet> tweets;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likes;

    @Enumerated(EnumType.STRING)
    private Role role;
}
