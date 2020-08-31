package com.zhlob.auto.repository.impl;

import com.zhlob.auto.domain.User;
import com.zhlob.auto.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private SessionFactory sessionFactory;
    private final String getUserQuery = "FROM User where login=:login";

    @Override
    public boolean registerUser(User user) {
        Session session = sessionFactory.openSession();
        Long result = (Long) session.save(user);
        session.flush();
        session.close();
        return result > 0;
    }

    @Override
    public User getUser(String login) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery(getUserQuery).setParameter("login", login).getSingleResult();
        session.close();
        return user;
    }
}
