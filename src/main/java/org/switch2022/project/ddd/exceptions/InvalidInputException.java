package org.switch2022.project.ddd.exceptions;

public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException(String message) {
        super(message);
    }
}

