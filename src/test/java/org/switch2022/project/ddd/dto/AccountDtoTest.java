package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Test;
import org.switch2022.project.dto.AccountDto;

import static org.junit.jupiter.api.Assertions.*;

class AccountDtoTest {

    /**
     * equals(Object toCompare)
     * Scenario 1 : ensure same AccountDto equals itself.
     */
    @Test
    void ensureSameAccountDtoEqualsItself() {
        // ARRANGE
        org.switch2022.project.dto.AccountDto reference = new org.switch2022.project.dto.AccountDto("John", "john@isep.ipp.pt", true);

        org.switch2022.project.dto.AccountDto other = reference;

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
        org.switch2022.project.dto.AccountDto reference = new org.switch2022.project.dto.AccountDto("John", "john@isep.ipp.pt", true);


        org.switch2022.project.dto.AccountDto other = new org.switch2022.project.dto.AccountDto("Mary", "mary@isep.ipp.pt", true);

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
        org.switch2022.project.dto.AccountDto reference = new org.switch2022.project.dto.AccountDto("John", "john@isep.ipp.pt", true);

        Object other = new Object();

        boolean expected = false;

        // ACT
        boolean result = reference.equals(other);

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
        org.switch2022.project.dto.AccountDto reference = new org.switch2022.project.dto.AccountDto("John", "john@isep.ipp.pt", true);

        org.switch2022.project.dto.AccountDto other = new org.switch2022.project.dto.AccountDto("John", "john@isep.ipp.pt", true);

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }

    /**
     *Scenario 2 : ensure AccountDto have different hash code.
     */
    @Test
    void ensureAccountDtoHaveDifferentHashCode() {
        // ARRANGE
        org.switch2022.project.dto.AccountDto reference = new org.switch2022.project.dto.AccountDto("John", "john@isep.ipp.pt", true);


        org.switch2022.project.dto.AccountDto other = new AccountDto("Mary", "mary@isep.ipp.pt", false);

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }



}