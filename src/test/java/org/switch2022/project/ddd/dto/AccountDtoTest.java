package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AccountDtoTest {

    /**
     * equals(Object toCompare)
     * Scenario 1 : ensure same AccountDto equals itself.
     */
    @Test
    void ensureSameAccountDtoEqualsItself() {
        // ARRANGE
        AccountDto reference = new AccountDto("John", "john@isep.ipp.pt", "true");

        AccountDto other = reference;

        boolean expected = true;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 2 : ensure two accountDto are not equals.
     */
    @Test
    void ensureTwoAccountDtoAreNotEqual() {
        // ARRANGE
        AccountDto reference = new AccountDto("John", "john@isep.ipp.pt", "true");


        AccountDto other = new AccountDto("Mary", "mary@isep.ipp.pt", "true");

        boolean expected = false;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 3 : ensure accountDto are not equal to other object.
     */
    @Test
    void ensureAccountDtoNotEqualsOtherTypeObject() {
        // ARRANGE
        AccountDto reference = new AccountDto("John", "john@isep.ipp.pt", "true");

        String other = "User";

        boolean expected = false;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: ensure AccountDto does not equal a different type of object.
     */
    @Test
    void ensureAccountDtoNotEqualsDifferentObjectType() {
        // ARRANGE
        AccountDto accountDto = new AccountDto("John", "john@isep.ipp.pt", "Planned");
        AccountDto differentObject = null;

        boolean expected = false;

        // ACT
        boolean result = accountDto.equals(differentObject);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * hashCode()
     * Scenario 1 : ensure AccountDto have same hash code.
     */
    @Test
    void ensureAccountDtoHaveSameHashCode() {
        // ARRANGE
        AccountDto reference = new AccountDto("John", "john@isep.ipp.pt", "true");

        AccountDto other = new AccountDto("John", "john@isep.ipp.pt", "true");

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }

    /**
     * Scenario 2 : ensure AccountDto have different hash code.
     */
    @Test
    void ensureAccountDtoHaveDifferentHashCode() {
        // ARRANGE
        AccountDto reference = new AccountDto("John", "john@isep.ipp.pt", "true");


        AccountDto other = new AccountDto("Mary", "mary@isep.ipp.pt", "false");

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }

    /**
     * Scenario 3 : ensure HashCode returns non zero value.
     */
    @Test
    void ensureHashCodeReturnsNonZeroValue() {
        // ARRANGE
        AccountDto accountDto = new AccountDto("John", "john@isep.ipp.pt", "Planned");

        // ACT
        int hashCode = accountDto.hashCode();

        // ASSERT
        assertNotEquals(0, hashCode);
    }

}