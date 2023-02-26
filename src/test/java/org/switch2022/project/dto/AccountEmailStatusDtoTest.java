package org.switch2022.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountEmailStatusDtoTest {

    /**
     * equals(Object toCompare)
     */
    @Test
    void ensureSameAccountDtoEqualsItself() {
        // ARRANGE
        AccountEmailStatusDto reference = new AccountEmailStatusDto( "john@isep.ipp.pt", true);

        AccountEmailStatusDto other = reference;

        boolean expected = true;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureTwoAccountDtoAreNotEqual() {
        // ARRANGE
        AccountEmailStatusDto reference = new AccountEmailStatusDto("john@isep.ipp.pt", true);


        AccountEmailStatusDto other = new AccountEmailStatusDto("mary@isep.ipp.pt", true);

        boolean expected = false;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureAccountDtoNotEqualsOtherTypeObject() {
        // ARRANGE
        AccountEmailStatusDto reference = new AccountEmailStatusDto( "john@isep.ipp.pt", true);

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
        AccountEmailStatusDto reference = new AccountEmailStatusDto( "john@isep.ipp.pt", true);

        AccountEmailStatusDto other = new AccountEmailStatusDto( "john@isep.ipp.pt", true);

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }

    @Test
    void ensureAccountDtoHaveDifferentHashCode() {
        // ARRANGE
        AccountEmailStatusDto reference = new AccountEmailStatusDto("john@isep.ipp.pt", true);


        AccountEmailStatusDto other = new AccountEmailStatusDto("mary@isep.ipp.pt", false);

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }
}