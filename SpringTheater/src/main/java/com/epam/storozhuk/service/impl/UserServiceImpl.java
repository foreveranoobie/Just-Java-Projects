package com.epam.storozhuk.service.impl;

import java.util.List;
import com.epam.storozhuk.dao.UserDAO;
import com.epam.storozhuk.domain.User;
import com.epam.storozhuk.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void remove(User user) {
        userDAO.remove(user);
    }

    @Override
    public User getById(int id) {
        return userDAO.getById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getUsersList();
    }

    @Override
    public User authorizeUser(String username) {
        return null;
    }
}
