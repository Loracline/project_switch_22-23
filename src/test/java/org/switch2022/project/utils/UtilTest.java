package org.switch2022.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilTest {

    /**
     * Testing the isLower() method.
     */
    @Test
    public void testIsLower_True() {
        // Arrange
        float first = 1.5f;
        float second = 2.5f;
        boolean expected = true;

        // Act
        boolean result = Util.isLower(first, second);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testIsLower_False() {
        // Arrange
        float first = 3.5f;
        float second = 2.5f;
        boolean expected = false;

        // Act
        boolean result = Util.isLower(first, second);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testIsLower_Equal() {
        // Arrange
        float first = 3.5f;
        float second = 3.5f;
        boolean expected = false;

        // Act
        boolean result = Util.isLower(first, second);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testIsLower_Negative() {
        // Arrange
        float first = -3.5f;
        float second = -2.5f;
        boolean expected = true;

        // Act
        boolean result = Util.isLower(first, second);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Testing the sum() method.
     */
    @Test
    public void testSum_Positive() {
        // Arrange
        float first = 1.5f;
        float second = 2.5f;
        float expected = 4.0f;

        // Act
        float result = Util.sum(first, second);

        // Assert
        assertEquals(expected, result, 0.001f);
    }

    @Test
    public void testSum_Negative() {
        // Arrange
        float first = -1.5f;
        float second = -2.5f;
        float expected = -4.0f;

        // Act
        float result = Util.sum(first, second);

        // Assert
        assertEquals(expected, result, 0.001f);
    }

    @Test
    public void testSum_Zero() {
        // Arrange
        float first = 0.0f;
        float second = 0.0f;
        float expected = 0.0f;

        // Act
        float result = Util.sum(first, second);

        // Assert
        assertEquals(expected, result, 0.001f);
    }

    /**
     * Testing the isLowerOrEqual() method.
     */
    @Test
    void ensureFirstIsLowerAndNotEqualSecond() {
        // Arrange
        int first = 1;
        int second = 2;
        boolean expected = true;

        // Act
        boolean result = Util.isLowerOrEqual(first, second);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureFirstIsEqualSecond() {
        // Arrange
        int first = 1;
        int second = 1;
        boolean expected = true;

        // Act
        boolean result = Util.isLowerOrEqual(first, second);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureFirstNotLowerThanSecond() {
        // Arrange
        int first = 2;
        int second = 1;
        boolean expected = false;

        // Act
        boolean result = Util.isLowerOrEqual(first, second);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureFirstLowerSecondAndEqualThird() {
        // Arrange
        int first = 1;
        int second = 2;
        int third = 1;
        boolean expected = true;

        // Act
        boolean result = Util.isLowerOrEqual(first, second) && Util.isLowerOrEqual(first, third);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Testing the areBothConditionsGranted() method.
     */
    @Test
    public void testAreBothConditionsGranted_TrueTrue() {
        // Arrange
        boolean first = true;
        boolean second = true;
        boolean expected = true;

        // Act
        boolean result = Util.areBothConditionsGranted(first, second);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testAreBothConditionsGranted_TrueFalse() {
        // Arrange
        boolean first = true;
        boolean second = false;
        boolean expected = false;

        // Act
        boolean result = Util.areBothConditionsGranted(first, second);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testAreBothConditionsGranted_FalseTrue() {
        // Arrange
        boolean first = false;
        boolean second = true;
        boolean expected = false;

        // Act
        boolean result = Util.areBothConditionsGranted(first, second);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testAreBothConditionsGranted_FalseFalse() {
        // Arrange
        boolean first = false;
        boolean second = false;
        boolean expected = false;

        // Act
        boolean result = Util.areBothConditionsGranted(first, second);

        // Assert
        assertEquals(expected, result);
    }
}