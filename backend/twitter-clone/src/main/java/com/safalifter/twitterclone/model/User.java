package com.safalifter.twitterclone.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity(name = "users")
@SuperBuilder
@RequiredArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {
    private String name;
    private String email;
    private String username;
    private String password;
    private LocalDate birthday;
}
