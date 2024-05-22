package com.develhope.spring.features.errors;

public class UserError {

    public static class UserNotFound extends GenericError {
        public UserNotFound() {
            super(435,"User not found");
        }
    }

    public static class UserIdNotFoundExc extends GenericError{
        public UserIdNotFoundExc(Long userId, Exception e) {
            super(435, "No user for the id "+ userId+ ". Caught exception: " + e.getMessage());
        }
    }


}
