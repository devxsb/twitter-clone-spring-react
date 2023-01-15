package com.safalifter.twitterclone.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    @Size(min = 8, max = 16)
    private String password;

    private LocalDate birthday;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tweet> tweets;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likes;
}
