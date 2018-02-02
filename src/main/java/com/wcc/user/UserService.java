package com.wcc.user;

public interface UserService {
    /*
        * finds a user by their associated email.
        @param targetEmail a email to find the database
        @return User
     */
    User findUserByEmail(String targetEmail);

    /*
        * finds a user by their associated email.
        @param targetEmail a email to find the database
        @return User
    */
    User findUserByMobileNumber(String targerNumber);
}
