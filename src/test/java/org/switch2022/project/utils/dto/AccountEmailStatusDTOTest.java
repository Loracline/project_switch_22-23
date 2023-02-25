package org.switch2022.project.utils.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountEmailStatusDTOTest {

    /**
     * equals(Object toCompare)
     */
    @Test
    void ensureSameAccountDTOEqualsItself() {
        // ARRANGE
        AccountEmailStatusDTO reference = new AccountEmailStatusDTO( "john@isep.ipp.pt", true);

        AccountEmailStatusDTO other = reference;

        boolean expected = true;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureTwoAccountDTOsAreNotEqual() {
        // ARRANGE
        AccountEmailStatusDTO reference = new AccountEmailStatusDTO("john@isep.ipp.pt", true);


        AccountEmailStatusDTO other = new AccountEmailStatusDTO("mary@isep.ipp.pt", true);

        boolean expected = false;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureAccountDTONotEqualsOtherTypeObject() {
        // ARRANGE
        AccountEmailStatusDTO reference = new AccountEmailStatusDTO( "john@isep.ipp.pt", true);

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
        AccountEmailStatusDTO reference = new AccountEmailStatusDTO( "john@isep.ipp.pt", true);

        AccountEmailStatusDTO other = new AccountEmailStatusDTO( "john@isep.ipp.pt", true);

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }

    @Test
    void ensureAccountDTOsHaveDifferentHashCode() {
        // ARRANGE
        AccountEmailStatusDTO reference = new AccountEmailStatusDTO("john@isep.ipp.pt", true);


        AccountEmailStatusDTO other = new AccountEmailStatusDTO("mary@isep.ipp.pt", false);

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }
}