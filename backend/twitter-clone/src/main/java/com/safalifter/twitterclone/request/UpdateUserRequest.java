package com.safalifter.twitterclone.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
public class UpdateUserRequest {
    private String name;

    private String username;

    @Email
    private String email;

    @Size(min = 8, max = 16)
    @Pattern(message = "Password must have at least 1 uppercase 1 lowercase and 1 number",
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    private String password;

    private LocalDate birthday;
}