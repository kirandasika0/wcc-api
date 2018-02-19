package com.wcc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findUserByEmail(String targetEmail) {
        Stream<User> userStream = StreamSupport.stream(userRepository.findAll().spliterator(), false);
        return userStream
                .filter(user -> user.getEmail().equals(targetEmail))
                .findFirst();
    }

    @Override
    public User findUserByMobileNumber(String targerNumber) {
        return null;
    }
}
