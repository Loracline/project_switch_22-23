package org.switch2022.project.utils.dto;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AccountDTOTest {
    Profile user;

    /**
     * equals(Object toCompare)
     */
    @Test
    void ensureSameAccountDTOEqualsItself() {
        // ARRANGE
        AccountDTO reference = new AccountDTO();
        reference.name = "John";
        reference.email = "john@isep.ipp.pt";
        reference.phoneNumber = 912345678;
        reference.photo = null;
        reference.profile = user;
        reference.status = true;

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
        AccountDTO reference = new AccountDTO();
        reference.name = "John";
        reference.email = "john@isep.ipp.pt";
        reference.phoneNumber = 912345678;
        reference.photo = null;
        reference.profile = user;
        reference.status = true;

        AccountDTO other = new AccountDTO();
        other.name = "Mary";
        other.email = "mary@isep.ipp.pt";
        other.phoneNumber = 912345678;
        other.photo = null;
        other.profile = user;
        other.status = true;

        boolean expected = false;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureAccountDTONotEqualsOtherTypeObject() {
        // ARRANGE
        AccountDTO reference = new AccountDTO();
        reference.name = "John";
        reference.email = "john@isep.ipp.pt";
        reference.phoneNumber = 912345678;
        reference.photo = null;
        reference.profile = user;
        reference.status = true;

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
        AccountDTO reference = new AccountDTO();
        reference.name = "John";
        reference.email = "john@isep.ipp.pt";
        reference.phoneNumber = 912345678;
        reference.photo = null;
        reference.profile = user;
        reference.status = true;

        AccountDTO other = new AccountDTO();
        other.name = "John";
        other.email = "john@isep.ipp.pt";
        other.phoneNumber = 912345678;
        other.photo = null;
        other.profile = user;
        other.status = true;

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }

    @Test
    void ensureAccountDTOsHaveDifferentHashCode() {
        // ARRANGE
        AccountDTO reference = new AccountDTO();
        reference.name = "John";
        reference.email = "john@isep.ipp.pt";
        reference.phoneNumber = 912345678;
        reference.photo = null;
        reference.profile = user;
        reference.status = true;

        AccountDTO other = new AccountDTO();

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther,hashCodeReference);
    }
}