package org.switch2022.project.ddd.utils;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import java.time.LocalDate;

@Component
public final class Validate {

    private Validate() {
    }

    /**
     * Validate that the specified argument string is neither null nor a length of zero (no characters), otherwise
     * throwing an exception with the specified message.
     *
     * @param string  the string to check.
     * @param message the exception message if invalid.
     */
    public static void notEmpty(String string, String message) {
        if (string == null || string.length() == 0) {
            throw new InvalidInputException(message);
        }
    }

    /**
     * Validate that the specified argument is neither null nor blank, otherwise throwing an exception with the
     * specified message.
     *
     * @param string  the string to check.
     * @param message the exception message if invalid.
     */
    public static void notBlank(String string, String message) {
        if (string == null || string.isBlank()) {
            throw new InvalidInputException(message);
        }
    }

    /**
     * Validate that the specified argument is not null or empty or blank, otherwise throwing an exception with the
     * specified message.
     *
     * @param string       the string to check.
     * @param argumentName the name of the argument to check.
     */
    public static void notNullOrEmptyOrBlank(String string, String argumentName) {
        notNull(string, String.format("The %s must not be null", argumentName));
        notEmpty(string, String.format("The %s must not be empty", argumentName));
        notBlank(string, String.format("The %s must not be blank", argumentName));
    }

    /**
     * Validate that the email has the following structure letters@letters.letters, otherwise throwing an exception
     * with the specified message.
     *
     * @param email the string to check.
     * @return TRUE if e-mail is valid and FALSE otherwise.
     */
    public static boolean isEmailValid(String email) {
        if (!email.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidInputException("The email is invalid");
        }
        return true;
    }

    /**
     * Validate if the phone number is portuguese or spanish, otherwise throws an exception with the specified message.
     *
     * @param phoneNumber the string to check.
     */
    public static void isPhoneNumberValid(String phoneNumber) {
        if (!phoneNumber.matches("^\\+3519\\d{8}|9\\d{8}|2\\d{8}|\\+346\\d{8}|\\+347\\d{8}|6\\d{8}|7\\d{8}$")) {
            throw new InvalidInputException("The phone number is invalid");
        }
    }

    /**
     * Validate that the specified argument is not negative, otherwise throwing an exception with the specified
     * message.
     *
     * @param number       the number to check.
     * @param argumentName the name of the argument to check.
     * @param <T>          class from where number extends from.
     */
    public static <T extends Number> void notNegative(T number, String argumentName) {
        if (number.doubleValue() < 0) {
            throw new InvalidInputException(String.format("The %s must not be negative",
                    argumentName));
        }
    }

    /**
     * Validate that the specified argument is not zero, otherwise throwing an exception with the specified message.
     *
     * @param number       the number to check.
     * @param argumentName the name of the argument to check.
     * @param <T>          class from where number extends from.
     */
    public static <T extends Number> void notZero(T number, String argumentName) {
        if (number.doubleValue() == 0.0) {
            throw new InvalidInputException(String.format("The %s must not be zero",
                    argumentName));
        }
    }

    /**
     * Validate that the specified argument is within a specified interval, otherwise throwing an exception with the
     * specified message.
     *
     * @param lowerLimit   the lower limit of the interval.
     * @param upperLimit   the upper limit of the interval.
     * @param value        the number to check.
     * @param argumentName the name of the argument to check.
     * @param <T>          class from where number extends from.
     */
    public static <T extends Number> void withinInterval(T lowerLimit, T upperLimit,
                                                         T value, String argumentName) {
        notNull(lowerLimit, "The lower limit must not be null");
        notNull(upperLimit, "The upper limit must not be null");
        notNull(value, "The value to check must not be null");

        if (value.doubleValue() < lowerLimit.doubleValue() || value.doubleValue() > upperLimit.doubleValue()) {
            throw new InvalidInputException(String.format("The %s must be between %s and %s",
                    argumentName,
                    lowerLimit, upperLimit));
        }
    }

    /**
     * Validate that the specified argument is not null, otherwise throwing an exception with the specified message.
     *
     * @param object  the object to check.
     * @param message the exception message if invalid.
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new InvalidInputException(message);
        }
    }

    /**
     * Validate that the specified date is not before another date, otherwise throwing an exception with the specified
     * message.
     *
     * @param dateOfInterest the date to check.
     * @param dateToCompare  the data to compare to.
     */
    public static void isAfter(LocalDate dateOfInterest, LocalDate dateToCompare) {
        if (dateOfInterest.isBefore(dateToCompare)) {
            throw new InvalidInputException(String.format("The date must be after %s",
                    dateToCompare));
        }
    }
}
