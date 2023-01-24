package org.switch2022.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {
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
        boolean result = Helper.isLower(first, second);

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
        boolean result = Helper.isLower(first, second);

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
        boolean result = Helper.isLower(first, second);

        // ASSERT
        assertEquals(expected, result);
    }
}