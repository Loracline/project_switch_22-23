package org.switch2022.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HelperTest {
    /**
     * Testing the isLower() method.
     */
    @Test
    void ensureFirstIndexIsLowerThanSecond() {
        assertTrue(Helper.isLower(1, 2));
    }

    @Test
    void ensureSecondIndexIsNotLowerThanFirst() {
        assertFalse(Helper.isLower(2, 1));
    }

    @Test
    void ensureSecondIndexIsNotLowerThanSecond() {
        assertFalse(Helper.isLower(2, 2));
    }
}