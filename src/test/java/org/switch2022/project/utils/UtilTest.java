package org.switch2022.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilTest {

    /**
     * Testing the isLower() method.
     */
    @Test
    void ensureFirstLowerSecond() {
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
    void ensureFirstNotLowerSecond() {
        // ARRANGE
        int first = 1;
        int second = 0;
        boolean expected = false;

        // ACT
        boolean result = Util.isLower(first, second);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureTwoIntegersBeingEqualIsFalse() {
        // ARRANGE
        int first = 1;
        int second = 1;
        boolean expected = false;

        // ACT
        boolean result = Util.isLower(first, second);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureFirstIndexNotLowerSecondAndThird() {
        // ARRANGE
        int first = 1;
        int second = 1;
        int three = 2;
        boolean expected = false;

        // ACT
        boolean result = Util.isLower(first, second) && Util.isLower(three, second);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureFirstIndexLowerThanThirdButNotLowerSecond() {
        // ARRANGE
        int first = 1;
        int second = 1;
        int three = 2;
        boolean expected = false;

        // ACT
        boolean result = Util.isLower(first, three) && Util.isLower(first, second);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * Testing the sum() method.
     */
    @Test
    void ensureSumIsRetrievedSuccessfully() {
        // Arrange
        float expected = 1;

        // Act
        float result = Util.sum(0f, 1f);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureSumIsRetrievedSuccessfullyWithZero() {
        // Arrange
        float expected = 0;

        // Act
        float result = Util.sum(0f, 0f);

        // Assert
        assertEquals(expected, result);
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
        boolean result = Util.isLowerOrEqual(first, second) && Util.isLowerOrEqual(first,third);

        // Assert
        assertEquals(expected, result);
    }
}