package org.switch2022.project.ddd.exceptions;

/**
 * Exception thrown when a customer cannot be found.
 */
public class CustomerNotFoundException extends RuntimeException {
    /**
     * Constructs a new CustomerNotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
