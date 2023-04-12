package org.switch2022.project.ddd.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    /**
     * METHOD getIntFromAlphanumericString(String fullExpression, String expressionToRemove)
     * Scenario 1: verifies that the initial expression is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatThrowsExceptionWhenInitialExpressionIsNull() {
        //Arrange
        String initialExpression = null;
        String expressionToRemove = "P";

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () ->
                Utils.getIntFromAlphanumericString(initialExpression, expressionToRemove));

    }

    /**
     * Scenario 2: verifies that the expression to remove is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatThrowsExceptionWhenExpressionToRemoveIsNull() {
        //Arrange
        String initialExpression = "P001";
        String expressionToRemove = null;

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () ->
                Utils.getIntFromAlphanumericString(initialExpression, expressionToRemove));

    }

    /**
     * Scenario 3: verifies that the number is successfully retrieved from the expression.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatReturnsTheNumberWithinTheAlphanumericExpression() {
        //Arrange
        String initialExpression = "P001";
        String expressionToRemove = "P";
        int expected = 1;

        //Act
        int result = Utils.getIntFromAlphanumericString(initialExpression, expressionToRemove);

        //Assert
        assertEquals(expected, result);
    }
}