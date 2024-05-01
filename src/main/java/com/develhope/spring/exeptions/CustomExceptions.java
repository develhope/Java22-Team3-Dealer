package com.develhope.spring.exeptions;

public class CustomExceptions {
    public static class AccessDeniedException extends RuntimeException {
        public AccessDeniedException(String message) {
            super(message);
        }
    }

    public static class InvalidStatusException extends RuntimeException {
        public InvalidStatusException(String message) {
            super(message);
        }
    }

    public static class InvalidIdException extends RuntimeException {
        public InvalidIdException(String message) {
            super(message);
        }
    }
    public static class InvalidTypeException extends RuntimeException {
        public InvalidTypeException(String message) {
            super(message);
        }
    }

}