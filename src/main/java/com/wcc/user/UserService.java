package com.wcc.user;

import java.util.Optional;

public interface UserService {
    /*
        * finds a user by their associated email.
        @param targetEmail a email to find the database
        @return User
     */
    Optional<User> findUserByEmail(String targetEmail);

    /*
        * finds a user by their associated email.
        @param targetEmail a email to find the database
        @return User
    */
    User findUserByMobileNumber(String targerNumber);
}
