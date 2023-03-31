package org.switch2022.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateTest {

    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsNull() {
        //Arrange
        String message = "The string must not be null";
        String stringToTest = null;

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () ->
                Validate.notNull(stringToTest, message));
    }

    @Test
    void ensureThatAnExceptionIsNotThrownWhenStringIsNotNull() {
        //Arrange
        String message = "The string must not be null";
        String stringToTest = "Not a null String";

        //Act and Assert
        assertDoesNotThrow(() ->
                Validate.notNull(stringToTest, message));
    }

    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsEmpty() {
        //Arrange
        String message = "The string must not be empty";
        String stringToTest = "";

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () ->
                Validate.notEmpty(stringToTest, message));
    }

    @Test
    void ensureThatAnExceptionIsNotThrownWhenStringIsNotEmpty() {
        //Arrange
        String message = "The string must not be empty";
        String stringToTest = "Not an empty String";

        //Act and Assert
        assertDoesNotThrow(() ->
                Validate.notEmpty(stringToTest, message));
    }


    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsBlank() {
        //Arrange
        String message = "The string must not be blank";
        String stringToTest = "    ";

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () ->
                Validate.notBlank(stringToTest, message));
    }

    @Test
    void ensureThatAnExceptionIsNotThrownWhenStringIsNotBlank() {
        //Arrange
        String message = "The string must not be blank";
        String stringToTest = "Not a blank String";

        //Act and Assert
        assertDoesNotThrow(() ->
                Validate.notBlank(stringToTest, message));
    }

    @Test
    void ensureThatAnExceptionIsThrownWhenIntegerIsNegative() {
        //Arrange
        String message = "The integer must not be negative";
        int integerToTest = -1;

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () ->
                Validate.notNegative(integerToTest, message));
    }

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