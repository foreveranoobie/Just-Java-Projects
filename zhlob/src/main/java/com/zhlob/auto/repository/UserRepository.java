package com.zhlob.auto.repository;

import com.zhlob.auto.domain.User;

public interface UserRepository {
    boolean registerUser(User user);

    User getUser(String login);
}
