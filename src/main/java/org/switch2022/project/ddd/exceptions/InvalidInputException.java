package org.switch2022.project.ddd.exceptions;

/**
 * An exception that is thrown when an invalid input is detected.
 * This exception extends the {@link IllegalArgumentException} class and is typically thrown
 * when a method receives an argument that is not valid, such as null or an empty string.
 */
public class InvalidInputException extends IllegalArgumentException {

    public InvalidInputException(String message) {
        super(message);
    }
}

