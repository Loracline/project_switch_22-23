package org.switch2022.project.ddd.exceptions;

/**
 * Exception to be thrown when a tax ID number is invalid.
 */
public class InvalidTaxIdException extends IllegalArgumentException {

    /**
     * Constructs an {@code InvalidTaxIdException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidTaxIdException(String message) {
        super(message);
    }
}
