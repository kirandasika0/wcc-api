package com.wcc.util;
/*
    This class will represent a default error response in the REST backend.
 */
public class Error {
    // Response status code
    private Integer statusCode;

    // Error string for a particular error.
    private String errorString;

    /*
        Constructor takes two arguments and creates a new error
        that is serialized to JSON automatically.
        @param statusCodeIn an int to represent HTTP status code
        @param errorStringIn human readable error string.
     */
    public Error(Integer statusCodeIn, String errorStringIn) {
        this.statusCode = statusCodeIn;
        this.errorString = errorStringIn;
    }
}
