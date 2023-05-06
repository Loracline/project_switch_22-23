package org.switch2022.project.ddd.exceptions;

/**
 * This exception is thrown to indicate that the object being searched for in a repository could not be found.
 * <p>
 * It is a subclass of RuntimeException and inherits its behavior and properties.
 */
public class NotFoundInRepoException extends RuntimeException {

    /**
     * Constructs a NotFoundInRepoException with the specified detail message.
     *
     * @param message the detail message. It should describe the reason for the exception being thrown.
     */
    public NotFoundInRepoException(String message) {
        super(message);
    }
}
