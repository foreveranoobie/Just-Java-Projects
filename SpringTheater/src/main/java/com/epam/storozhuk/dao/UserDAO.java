package com.epam.storozhuk.dao;

import java.util.ArrayList;
import java.util.List;
import com.epam.storozhuk.domain.User;

public class UserDAO {
    private List<User> usersList;

    public UserDAO() {
        usersList = new ArrayList<>();
    }

    public UserDAO(List<User> usersList) {
        this.usersList = new ArrayList<>(usersList);
    }

    public void save(User user) {
        if (!usersList.contains(user)) {
            user.setId(getLatestId() + 1);
            usersList.add(user);
        }
    }

    public void remove(User user) {
        usersList.remove(user);
    }

    public User getById(int id) {
        for (User user : usersList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User getUserByEmail(String email) {
        for (User user : usersList) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;

    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    boolean authorizeUser(String user) {
        return false;
    }

    private int getLatestId() {
        int max = 0;
        for (User user : usersList) {
            if (user.getId() > max) {
                max = user.getId();
            }
        }
        return max;
    }
}
