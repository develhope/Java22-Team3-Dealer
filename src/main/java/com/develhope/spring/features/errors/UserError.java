package com.develhope.spring.features.errors;

public class UserError {

    public static class UserNotFound extends GenericErrors {
        public UserNotFound() {
            super(435,"User not found");
        }
    }

    public static class UserIdNotFoundExc extends GenericErrors {
        public UserIdNotFoundExc(Long userId, Exception e) {
            super(435, "No user for the id "+ userId+ ". Caught exception: " + e.getMessage());
        }
    }


}
