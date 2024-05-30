package com.develhope.spring.features.errors;

public class UserError {

    public static class UserNotFound extends GenericErrors {
        public UserNotFound() {
            super(433,"User not found");
        }
    }

    public static class UserIdNotFoundExc extends GenericErrors {
        public UserIdNotFoundExc(Long userId, Exception e) {
            super(434, "No user for the id "+ userId+ ". Caught exception: " + e.getMessage());
        }
    }

    public static class ImpossibleToCreateUser extends GenericErrors {
        public ImpossibleToCreateUser(Exception e) {
            super(435, "Failed to create user: " + e.getMessage());
        }
    }

    public static class UserIsEmpty extends GenericErrors {
        public UserIsEmpty() {
            super(436, "User is empty");
        }
    }
    public static class DefaultUser extends GenericErrors {
        public DefaultUser() {
            super(437, "User is default, impossible to execute any action");
        }
    }

    public static class InvalidUser extends GenericErrors {
        public InvalidUser() {
            super(438, "Access denied, action executable only by admins");
        }
    }
}
