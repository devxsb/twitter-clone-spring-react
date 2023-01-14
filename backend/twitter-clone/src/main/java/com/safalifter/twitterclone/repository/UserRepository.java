package com.safalifter.twitterclone.repository;

import com.safalifter.twitterclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
