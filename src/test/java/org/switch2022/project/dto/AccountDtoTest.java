package org.switch2022.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AccountDtoTest {
    /**
     * equals(Object toCompare)
     */
    @Test
    void ensureSameAccountDTOEqualsItself() {
        // ARRANGE
        AccountDto reference = new AccountDto("John", "john@isep.ipp.pt", true);

        AccountDto other = reference;

        boolean expected = true;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureTwoAccountDTOsAreNotEqual() {
        // ARRANGE
        AccountDto reference = new AccountDto("John", "john@isep.ipp.pt", true);


        AccountDto other = new AccountDto("Mary", "mary@isep.ipp.pt", true);

        boolean expected = false;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureAccountDTONotEqualsOtherTypeObject() {
        // ARRANGE
        AccountDto reference = new AccountDto("John", "john@isep.ipp.pt", true);

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
        AccountDto reference = new AccountDto("John", "john@isep.ipp.pt", true);

        AccountDto other = new AccountDto("John", "john@isep.ipp.pt", true);

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }

    @Test
    void ensureAccountDTOsHaveDifferentHashCode() {
        // ARRANGE
        AccountDto reference = new AccountDto("John", "john@isep.ipp.pt", true);


        AccountDto other = new AccountDto("Mary", "mary@isep.ipp.pt", false);

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }
}