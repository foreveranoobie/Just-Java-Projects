package com.epam.storozhuk.service;

import java.util.List;
import com.epam.storozhuk.domain.User;

public interface UserService {
    void save(User user);

    void remove(User user);

    User getById(int id);

    User getUserByEmail(String email);

    List<User> getAll();

    User authorizeUser(String username);
}
