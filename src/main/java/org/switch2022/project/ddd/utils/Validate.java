package org.switch2022.project.ddd.utils;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import java.time.LocalDate;

@Component

public final class Validate {

    /**
     * Utility classes typically provide a set of related functionality that can be used across
     * different parts of an
     * application. They are designed to be stateless and provide only static methods. Since
     * utility classes only
     * provide static methods, there is no need to create instances of the class.
     * <br>
     * By making the constructor of the utility class private, we prevent any instances of the
     * class from being
     * created. This ensures that the utility class remains stateless and can only be used
     * through its static methods.
     */

    private Validate() {
    }

    //STRING VALIDATIONS

    /**
     * <p>Validate that the specified argument string is
     * neither <code>null</code> nor a length of zero (no characters);
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.notEmpty(myString, "The string must not be empty");</pre>
     *
     * @param string  the string to check
     * @param message the exception message if invalid
     * @throws InvalidInputException if the string is empty
     */
    public static void notEmpty(String string, String message) {
        if (string == null || string.length() == 0) {
            throw new InvalidInputException(message);
        }
    }

    /**
     * <p>Validate that the specified argument is
     * neither <code>null</code> nor <code>blank</code>;
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.notBlank(myString, "The string must not be blank");</pre>
     *
     * @param string  the string to check
     * @param message the exception message if invalid
     * @throws InvalidInputException if the string is blank
     */
    public static void notBlank(String string, String message) {
        if (string == null || string.isBlank()) {
            throw new InvalidInputException(message);
        }
    }

    /**
     * <p>Validate that the specified argument is not <code>null</code> or <code>empty</code> or
     * <code>blank</code>;
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.notNullOrEmptyOrBlank(myString, myStringName);</pre>
     *
     * @param string       the string to check.
     * @param argumentName the name of the argument to check.
     * @throws InvalidInputException if the string is null or empty or blank.
     */
    public static void notNullOrEmptyOrBlank(String string, String argumentName) {
        notNull(string, String.format("The %s must not be null", argumentName));
        notEmpty(string, String.format("The %s must not be empty", argumentName));
        notBlank(string, String.format("The %s must not be blank", argumentName));
    }

    /**
     * <p>Validate that the email has the following structure letters@letters.letters;
     * otherwise throwing an exception with the specified message.
     *
     * @param email the string to check.
     * @throws InvalidInputException if the string is null or empty or blank or email invalid.
     */
    public static void isEmailValid(String email) {
        if (!email.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidInputException("The email is invalid");
        }
    }

    /**
     * <p>Validate if the phone number is portuguese or spanish;
     * otherwise throws an exception with the specified message.
     *
     * @param phoneNumber the string to check.
     * @throws InvalidInputException if the string is null or empty or blank or email invalid.
     */
    public static void isPhoneNumberValid(String phoneNumber) {
        if (!phoneNumber.matches("^\\+3519\\d{8}|9\\d{8}|2\\d{8}|\\+346\\d{8}|\\+347\\d{8}|6\\d{8}|7\\d{8}$")) {
            throw new InvalidInputException("The phone number is invalid");
        }
    }

    //NUMBER VALIDATIONS

    /**
     * <p>Validate that the specified argument is not <code>negative</code>;
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.notNegative(myNumber, "The number must not be negative");</pre>
     *
     * @param number       the number to check.
     * @param argumentName the name of the argument to check.
     * @throws InvalidInputException if the number is negative.
     */
    public static <T extends Number> void notNegative(T number, String argumentName) {
        if (number.doubleValue() < 0) {
            throw new InvalidInputException(String.format("The %s must not be negative",
                    argumentName));
        }
    }

    /**
     * <p>Validate that the specified argument is not <code>zero</code>;
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.notZero(myNumber, "The number must not be zero");</pre>
     *
     * @param number       the number to check.
     * @param argumentName the name of the argument to check.
     * @throws InvalidInputException if the number is zero.
     */
    public static <T extends Number> void notZero(T number, String argumentName) {
        if (number.doubleValue() == 0.0) {
            throw new InvalidInputException(String.format("The %s must not be zero",
                    argumentName));
        }
    }

    /**
     * <p>Validate that the specified argument is within a specified <code>interval</code>;
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.withinInterval(myValueToCheck, "The valueToCheck must be between lowerLimit and upperLimit");
     * </pre>
     *
     * @param lowerLimit   the lower limit of the interval.
     * @param upperLimit   the upper limit of the interval.
     * @param value        the number to check.
     * @param argumentName the name of the argument to check.
     * @throws InvalidInputException if the number is outside the interval
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

    //OBJECT VALIDATIONS

    /**
     * <p>Validate that the specified argument is not <code>null</code>;
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.notNull(myObject, "The object must not be null");</pre>
     *
     * @param object  the object to check
     * @param message the exception message if invalid
     * @throws InvalidInputException if the object is null
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new InvalidInputException(message);
        }
    }

    //DATE VALIDATIONS

    /**
     * <p>Validate that the specified date is not before another date;
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.isDateAfter(myLocalDate, "The dateOfInterest must be after the
     * dateToCompare");</pre>
     *
     * @param dateOfInterest the date to check.
     * @param dateToCompare  the data to compare to.
     * @throws InvalidInputException if the date to check is before the date to compare to.
     */
    public static void isAfter(LocalDate dateOfInterest, LocalDate dateToCompare) {
        if (dateOfInterest.isBefore(dateToCompare)) {
            throw new InvalidInputException(String.format("The date must be after %s",
                    dateToCompare));
        }
    }
}
