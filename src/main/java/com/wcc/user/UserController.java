package com.wcc.user;


import com.wcc.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
// All URl routes prefixed with /user will enter this controller
@RequestMapping("/user")
public class UserController {
    // This instance variable will handle all database CRUD operations.
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public User indexRoute() {
        return new User();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public User newUser(@RequestBody User user) {
        User newUser = new User();
        newUser.setFullName(user.getFullName());
        newUser.setDisplayName(user.getDisplayName());
        newUser.setMobileNumber(user.getMobileNumber());

        // Save to database and return value
        userRepository.save(newUser);

        return newUser;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User findById(@PathVariable Long userId) throws UserNotFoundException{
        User currentUser = userRepository.findOne(userId);
        if (currentUser == null) {
            throw new UserNotFoundException("user with id not found");
        }

        return currentUser;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<User> findAll() {
        // Need to set up extra validation to ensure authenticity.
        Iterator<User> itr = userRepository.findAll().iterator();
        List<User> retCollection = new ArrayList<>();
        itr.forEachRemaining(retCollection::add);

        return retCollection;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable Long userId, @RequestBody User userDetails) {
        User currUser = userRepository.findOne(userId);
        currUser.setDisplayName(userDetails.getDisplayName() != null ? userDetails.getDisplayName() : currUser.getDisplayName());
        currUser.setFullName(userDetails.getFullName() != null ? userDetails.getFullName() : currUser.getFullName());
        currUser.setMobileNumber(userDetails.getMobileNumber() != null ? userDetails.getMobileNumber() : currUser.getMobileNumber());

        userRepository.save(currUser);

        return currUser;
    }

}
