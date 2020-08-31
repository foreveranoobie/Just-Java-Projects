package com.zhlob.auto.service;

import com.zhlob.auto.domain.Role;
import com.zhlob.auto.domain.User;
import com.zhlob.auto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean saveUser(User user) {
        user.setRole(new Role("ROLE_USER", 3));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.registerUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.getUser(s);
    }
}
