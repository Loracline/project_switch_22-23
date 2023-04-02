package org.switch2022.project.ddd.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateTest {
    /**
     * METHOD Validate.notNullOrEmptyOrBlank(myString, "The string must not be null/empty/blank")
     * Scenario 1: verifies if a String is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsNullInTheNotNullOrEmptyOrBlankMethod() {
        //Arrange
        String argumentName = "string";
        String expected = "The string must not be null";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                Validate.notNullOrEmptyOrBlank(null, argumentName));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if a String is empty.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsEmptyInTheNotNullOrEmptyOrBlankMethod() {
        //Arrange
        String argumentName = "string";
        String expected = "The string must not be empty";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                Validate.notNullOrEmptyOrBlank("", argumentName));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 3: verifies if a String is blank.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsBlankInTheNotNullOrEmptyOrBlankMethod() {
        //Arrange
        String argumentName = "string";
        String expected = "The string must not be blank";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                Validate.notNullOrEmptyOrBlank(" ", argumentName));


        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 6: verifies if a String is not null nor empty nor blank.
     * Should not throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsNotThrownWhenStringIsNotNullEmptyOrBlankInTheNotNullOrEmptyOrBlankMethod() {
        //Arrange
        String argumentName = "string";
        String stringToTest = "Not an null/empty/blank String";

        //Act and Assert
        assertDoesNotThrow(() ->
                Validate.notNullOrEmptyOrBlank(stringToTest, argumentName));
    }

    /**
     * METHOD Validate.notNull(myNumber, "The number must not be null")
     * Scenario 1: verifies if a number is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenNumberIsNull() {
        //Arrange
        String argumentName = "number";
        Number numberToTest = null;
        String expected = "The number must not be null";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                Validate.notNull(numberToTest, argumentName));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if a number is not null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsNotThrownWhenNumberIsNotNull() {
        //Arrange
        String argumentName = "number";
        Number numberToTest = 5;

        //Act and Assert
        assertDoesNotThrow(() -> Validate.notNull(numberToTest, argumentName));
    }

    /**
     * METHOD Validate.notNegative(myNumber, "The number must not be negative")
     * Scenario 1: verifies if a number is negative.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenNumberIsNegative() {
        //Arrange
        String argumentName = "number";
        Number numberToTest = -1;
        String expected = "The number must not be negative";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                Validate.notNegative(numberToTest, argumentName));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if a number is negative.
     * Should not throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsNotThrownWhenNumberIsNotNegative() {
        //Arrange
        String argumentName = "integer";
        Number numberToTest = 0;

        //Act and Assert
        assertDoesNotThrow(() ->
                Validate.notNegative(numberToTest, argumentName));
    }

    /**
     * METHOD Validate.withinInterval(myValueToCheck, "The valueToCheck must be between lowerLimit and upperLimit")
     * Scenario 1: verifies if a number is not located within an interval because it's lower than the lower limit.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenNumberIsBelowInterval() {
        //Arrange
        String argumentName = "number";
        Number lowerLimit = 1;
        Number upperLimit = 4;
        Number numberToTest = -1;
        String expected = "The number must be between 1 and 4";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                Validate.withinInterval(lowerLimit, upperLimit, numberToTest, argumentName));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if a number is not located within an interval because it's higher than the upper limit.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenNumberIsAboveInterval() {
        //Arrange
        String argumentName = "number";
        Number lowerLimit = 1;
        Number upperLimit = 4;
        Number numberToTest = 5;
        String expected = "The number must be between 1 and 4";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                Validate.withinInterval(lowerLimit, upperLimit, numberToTest, argumentName));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 3: verifies if a number is located within an interval.
     * Should not throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsNotThrownWhenNumberIsWithinInterval() {
        //Arrange
        String argumentName = "number";
        Number lowerLimit = 1;
        Number upperLimit = 4;
        Number numberToTest = 3;

        //Act and Assert
        assertDoesNotThrow(() ->
                Validate.withinInterval(lowerLimit, upperLimit, numberToTest, argumentName));
    }

    /**
     * Scenario 4: verifies if a number is located within an interval if is equal to the lower limit.
     * Should not throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsNotThrownWhenNumberIsWithinIntervalAndEqualToLowerLimit() {
        //Arrange
        String argumentName = "number";
        Number lowerLimit = 1;
        Number upperLimit = 4;
        Number numberToTest = 1;

        //Act and Assert
        assertDoesNotThrow(() ->
                Validate.withinInterval(lowerLimit, upperLimit, numberToTest, argumentName));
    }

    /**
     * Scenario 5: verifies if a number is located within an interval if is equal to the upper limit.
     * Should not throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsNotThrownWhenNumberIsWithinIntervalAndEqualToUpperLimit() {
        //Arrange
        String argumentName = "number";
        Number lowerLimit = 1;
        Number upperLimit = 4;
        Number numberToTest = 4;

        //Act and Assert
        assertDoesNotThrow(() ->
                Validate.withinInterval(lowerLimit, upperLimit, numberToTest, argumentName));
    }

    /**
     * METHOD Validate.notNull(myObject, "The object must not be null")
     * Scenario 1: verifies if an Object is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenObjectIsNull() {
        //Arrange
        String message = "The string must not be null";
        Object objectToTest = null;

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () ->
                Validate.notNull(objectToTest, message));
    }

    /**
     * Scenario 2: verifies if an Object is not null.
     * Should not throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsNotThrownWhenObjectIsNotNull() {
        //Arrange
        String message = "The string must not be null";
        Object objectToTest = new Object();

        //Act and Assert
        assertDoesNotThrow(() ->
                Validate.notNull(objectToTest, message));
    }
}