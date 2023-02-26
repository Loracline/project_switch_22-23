package org.switch2022.project.dto;

import org.junit.jupiter.api.Test;
import org.switch2022.project.dto.AccountDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AccountDTOTest {
    /**
     * equals(Object toCompare)
     */
    @Test
    void ensureSameAccountDTOEqualsItself() {
        // ARRANGE
        AccountDTO reference = new AccountDTO("John", "john@isep.ipp.pt", true);

        AccountDTO other = reference;

        boolean expected = true;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureTwoAccountDTOsAreNotEqual() {
        // ARRANGE
        AccountDTO reference = new AccountDTO("John", "john@isep.ipp.pt", true);


        AccountDTO other = new AccountDTO("Mary", "mary@isep.ipp.pt", true);

        boolean expected = false;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureAccountDTONotEqualsOtherTypeObject() {
        // ARRANGE
        AccountDTO reference = new AccountDTO("John", "john@isep.ipp.pt", true);

        Object other = new Object();

        boolean expected = false;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * hashCode()
     */
    @Test
    void ensureAccountDTOsHaveSameHashCode() {
        // ARRANGE
        AccountDTO reference = new AccountDTO("John", "john@isep.ipp.pt", true);

        AccountDTO other = new AccountDTO("John", "john@isep.ipp.pt", true);

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }

    @Test
    void ensureAccountDTOsHaveDifferentHashCode() {
        // ARRANGE
        AccountDTO reference = new AccountDTO("John", "john@isep.ipp.pt", true);


        AccountDTO other = new AccountDTO("Mary", "mary@isep.ipp.pt", false);

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }
}