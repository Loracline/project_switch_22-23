package org.switch2022.project.ddd.utils;

import static java.lang.Integer.parseInt;

public class Utils {

    /**
     * This method extracts the number of an alphanumeric string by removing a specified expression and converts them
     * into an integer value.
     * @param initialExpression initial alphanumeric string
     * @param expressionToRemove string to remove
     * @return the string number as an integer value.
     */
    public static int getIntFromAlphanumericString(String initialExpression, String expressionToRemove) {
        Validate.notNull(initialExpression, "The initial expression must not be null");
        Validate.notNull(expressionToRemove, "The expression to remove must not be null");

        String[] array = initialExpression.toLowerCase().split(expressionToRemove.toLowerCase(), -2);
        return parseInt(array[1]);
    }
}
