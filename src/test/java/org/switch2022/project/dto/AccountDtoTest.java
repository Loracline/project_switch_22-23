package org.switch2022.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AccountDtoTest {
    /**
     * equals(Object toCompare)
     */
    @Test
    void ensureSameAccountDtoEqualsItself() {
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
    void ensureTwoAccountDtoAreNotEqual() {
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
    void ensureAccountDtoNotEqualsOtherTypeObject() {
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
    void ensureAccountDtoHaveSameHashCode() {
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
    void ensureAccountDtoHaveDifferentHashCode() {
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