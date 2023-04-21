package org.switch2022.project.ddd.exceptions;

public class UserStoryAlreadyExistException extends RuntimeException {
    public UserStoryAlreadyExistException (String message) {
        super (message);
    }
}
