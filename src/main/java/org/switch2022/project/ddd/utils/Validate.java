package org.switch2022.project.ddd.utils;

public class Validate {

    //STRING VALIDATIONS

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
     * @param string       the string to check.
     * @param argumentName the name of the argument to check.
     * @throws IllegalArgumentException if the string is null or empty or blank.
     */
    public static void notNullOrEmptyOrBlank(String string, String argumentName) {
        notNull(string, String.format("The %s must not be null", argumentName));
        notEmpty(string, String.format("The %s must not be empty", argumentName));
        notBlank(string, String.format("The %s must not be blank", argumentName));
    }

    //NUMBER VALIDATIONS

    /**
     * <p>Validate that the specified numeric argument is not <code>null</code>;
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.notNull(myNumber, "The number must not be null");</pre>
     *
     * @param number       the number to check.
     * @param argumentName the name of the argument to check.
     * @throws IllegalArgumentException if the number is null.
     */
    public static <T extends Number> void notNull(T number, String argumentName) {
        if (number == null) {
            throw new IllegalArgumentException(String.format("The %s must not be null", argumentName));
        }
    }

    /**
     * <p>Validate that the specified argument is not <code>negative</code>;
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.notNegative(myNumber, "The number must not be negative");</pre>
     *
     * @param number       the number to check.
     * @param argumentName the name of the argument to check.
     * @throws IllegalArgumentException if the number is negative.
     */
    public static <T extends Number> void notNegative(T number, String argumentName) {
        if (number.doubleValue() < 0) {
            throw new IllegalArgumentException(String.format("The %s must not be negative", argumentName));
        }
    }

    /**
     * <p>Validate that the specified argument is within a specified <code>interval</code>;
     * otherwise throwing an exception with the specified message.
     *
     * <pre>Validate.withinInterval(myValueToCheck, "The valueToCheck must be between lowerLimit and upperLimit");
     * </pre>
     *
     * @param lowerLimit the lower limit of the interval.
     * @param upperLimit the upper limit of the interval.
     * @param valueToCheck      the number to check.
     * @param argumentName the name of the argument to check.
     * @throws IllegalArgumentException if the number is outside the interval
     */
    public static <T extends Number> void withinInterval(T lowerLimit, T upperLimit, T valueToCheck, String argumentName){
        if (valueToCheck.doubleValue() < lowerLimit.doubleValue() || valueToCheck.doubleValue() > upperLimit.doubleValue()){
            throw new IllegalArgumentException(String.format("The %s must be between %s and %s", argumentName,
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
     * @throws IllegalArgumentException if the object is null
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
