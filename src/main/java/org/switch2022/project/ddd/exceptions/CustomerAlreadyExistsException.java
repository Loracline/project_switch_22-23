package org.switch2022.project.ddd.exceptions;

/**
 * A custom exception that is thrown when an attempt is made to add a customer to a repository that already contains a
 * customer with the same tax ID.
 */
public class CustomerAlreadyExistsException extends RuntimeException {
    /**
     * Constructs a new CustomerAlreadyExistsException with the specified error message.
     *
     * @param message the error message for the exception.
     */
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
