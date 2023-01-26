package org.switch2022.project.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UtilTest {
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    void ensureExceptionIsThrownWhenTryingInstantiationOfClass() {
        // Arrange
        String expected = "Utility class";

        // Act
        IllegalStateException result = assertThrows(IllegalStateException.class,
                () -> {
                    Util util = new Util();
                });

        // Assert
        Assertions.assertEquals(expected, result.getMessage());
    }

    /**
     * Testing the isLower() method.
     */
    @Test
    void ensureFirstIndexIsLowerThanSecond() {
        // ARRANGE
        int first = 1;
        int second = 2;
        boolean expected = true;

        // ACT
        boolean result = Util.isLower(first, second);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureSecondIndexIsNotLowerThanFirst() {
        // ARRANGE
        int first = 2;
        int second = 1;
        boolean expected = false;

        // ACT
        boolean result = Util.isLower(first, second);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureSecondIndexIsNotLowerThanSecond() {
        // ARRANGE
        int first = 2;
        int second = 2;
        boolean expected = false;

        // ACT
        boolean result = Util.isLower(first, second);

        // ASSERT
        assertEquals(expected, result);
    }
}