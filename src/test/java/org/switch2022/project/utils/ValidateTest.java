package org.switch2022.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateTest {
    /**
     * METHOD Validate.notNull(myString, "The string must not be null")
     * Scenario 1: verifies if a String is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsNull() {
        //Arrange
        String message = "The string must not be null";
        String stringToTest = null;

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () ->
                Validate.notNull(stringToTest, message));
    }

    /**
     * Scenario 2: verifies if a String is not null.
     * Should not throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsNotThrownWhenStringIsNotNull() {
        //Arrange
        String message = "The string must not be null";
        String stringToTest = "Not a null String";

        //Act and Assert
        assertDoesNotThrow(() ->
                Validate.notNull(stringToTest, message));
    }

    /**
     * METHOD Validate.notEmpty(myString, "The string must not be empty")
     * Scenario 1: verifies if a String is empty.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsEmpty() {
        //Arrange
        String message = "The string must not be empty";
        String stringToTest = "";

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () ->
                Validate.notEmpty(stringToTest, message));
    }

    /**
     * Scenario 2: verifies if a String is empty.
     * Should not throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsNotThrownWhenStringIsNotEmpty() {
        //Arrange
        String message = "The string must not be empty";
        String stringToTest = "Not an empty String";

        //Act and Assert
        assertDoesNotThrow(() ->
                Validate.notEmpty(stringToTest, message));
    }

    /**
     * METHOD Validate.notBlank(myString, "The string must not be blank")
     * Scenario 1: verifies if a String is blank.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsBlank() {
        //Arrange
        String message = "The string must not be blank";
        String stringToTest = "    ";

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () ->
                Validate.notBlank(stringToTest, message));
    }

    /**
     * Scenario 2: verifies if a String is blank.
     * Should not throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsNotThrownWhenStringIsNotBlank() {
        //Arrange
        String message = "The string must not be blank";
        String stringToTest = "Not a blank String";

        //Act and Assert
        assertDoesNotThrow(() ->
                Validate.notBlank(stringToTest, message));
    }

    /**
     * METHOD Validate.notNegative(myInt, "The int must not be negative")
     * Scenario 1: verifies if an int is negative.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenIntegerIsNegative() {
        //Arrange
        String message = "The integer must not be negative";
        int integerToTest = -1;

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () ->
                Validate.notNegative(integerToTest, message));
    }

    /**
     * Scenario 2: verifies if an int is negative.
     * Should not throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsNotThrownWhenIntegerIsNotNegative() {
        //Arrange
        String message = "The integer must not be negative";
        int integerToTest = 0;

        //Act and Assert
        assertDoesNotThrow(() ->
                Validate.notNegative(integerToTest, message));
    }
}