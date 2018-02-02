package com.wcc.user;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByEmail(String targetEmail) {
        Iterator<User> allUsers = userRepository.findAll().iterator();
        User tempUser = allUsers.next();
        while (allUsers.hasNext()) {
            if (tempUser.getEmail().equals(targetEmail)) {
                return tempUser;
            }

            tempUser = allUsers.next();
        }
        return null;
    }

    @Override
    public User findUserByMobileNumber(String targerNumber) {
        return null;
    }
}
