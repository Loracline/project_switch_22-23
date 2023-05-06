package org.switch2022.project.ddd.exceptions;

/**
 * This exception is thrown to indicate that the object being added to a repository already exists in it.
 * <p>
 * It is a subclass of IllegalArgumentException and inherits its behavior and properties.
 */
public class AlreadyExistsInRepoException extends IllegalArgumentException {

    /**
     * Constructs an AlreadyExistsInRepoException with the specified detail message.
     *
     * @param message the detail message. It should describe the reason for the exception being thrown.
     */
    public AlreadyExistsInRepoException(String message) {
        super(message);
    }
}
