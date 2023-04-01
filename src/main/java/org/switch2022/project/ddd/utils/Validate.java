package org.switch2022.project.ddd.utils;

public class Validate {

    /**
     * <p>Validate that the specified argument is not <code>null</code>;
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.notNull(myString, "The string must not be null");</pre>
     *
     * @param string  the string to check
     * @param message the exception message if invalid
     * @throws IllegalArgumentException if the string is null
     */
    public static void notNull(String string, String message) {
        if (string == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * <p>Validate that the specified argument is not <code>null</code>;
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.notNull(myObject, "The object must not be null");</pre>
     *
     * @param object  the object to check
     * @param message the exception message if invalid
     * @throws IllegalArgumentException if the object is null
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * <p>Validate that the specified argument string is
     * neither <code>null</code> nor a length of zero (no characters);
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.notEmpty(myString, "The string must not be empty");</pre>
     *
     * @param string  the string to check
     * @param message the exception message if invalid
     * @throws IllegalArgumentException if the string is empty
     */
    public static void notEmpty(String string, String message) {
        if (string == null || string.length() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * <p>Validate that the specified argument is not <code>blank</code>;
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.notBlank(myString, "The string must not be blank");</pre>
     *
     * @param string  the string to check
     * @param message the exception message if invalid
     * @throws IllegalArgumentException if the string is blank
     */
    public static void notBlank(String string, String message) {
        if (string.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * <p>Validate that the specified argument is not <code>null</code> or <code>empty</code> or <code>blank</code>;
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.notNullOrEmptyOrBlank(myString, myStringName);</pre>
     *
     * @param string the string to check.
     * @param argumentName the name of the argument to check.
     * @throws IllegalArgumentException if the string is null or empty or blank.
     */
    public static void notNullOrEmptyOrBlank(String string, String argumentName) {
        notNull(string, String.format("The %s must not be null",argumentName));
        notEmpty(string, String.format("The %s must not be empty",argumentName));
        notBlank(string, String.format("The %s must not be blank",argumentName));
    }

    /**
     * <p>Validate that the specified argument is not <code>negative</code>;
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.notNegative(myInteger, "The int must not be negative");</pre>
     *
     * @param integer the int to check
     * @param message the exception message if invalid
     * @throws IllegalArgumentException if the int is negative
     */
    public static void notNegative(int integer, String message) {
        if (integer < 0) {
            throw new IllegalArgumentException(message);
        }
    }
}
