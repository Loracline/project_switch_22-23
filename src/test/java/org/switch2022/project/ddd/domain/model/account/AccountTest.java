package org.switch2022.project.ddd.domain.model.account;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.PhoneNumber;
import org.switch2022.project.ddd.domain.value_object.Photo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AccountTest {

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameAccountEqualsItself() {
        // Arrange
        Name nameDouble = mock(Name.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);

        Account reference = new Account(nameDouble, emailDouble, phoneNumberDouble, photoDouble);
        Account other = reference;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two objects with the same email are equal.
     */
    @Test
    void ensureTwoInstancesWithSameEmailAreEqual() {
        // Arrange
        Name nameDouble = mock(Name.class);
        Name nameDoubleTwo = mock(Name.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        PhoneNumber phoneNumberDoubleTwo = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        Photo photoDoubleTwo = mock(Photo.class);

        Account reference = new Account(nameDouble, emailDouble, phoneNumberDouble, photoDouble);
        Account other = new Account(nameDoubleTwo, emailDouble, phoneNumberDoubleTwo, photoDoubleTwo);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: Verify that two objects with different email are not equal.
     */
    @Test
    void ensureTwoDifferentPhotoInstancesAreNotTheSame() {
        // Arrange
        Name nameDouble = mock(Name.class);
        Name nameDoubleTwo = mock(Name.class);
        Email emailDouble = mock(Email.class);
        Email emailDoubleTwo = mock(Email.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        PhoneNumber phoneNumberDoubleTwo = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        Photo photoDoubleTwo = mock(Photo.class);

        Account reference = new Account(nameDouble, emailDouble, phoneNumberDouble, photoDouble);
        Account other = new Account(nameDoubleTwo, emailDoubleTwo, phoneNumberDoubleTwo, photoDoubleTwo);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if an Account instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureAccountDoesNotEqualOtherTypeOfObject() {
        // Arrange
        Name nameDouble = mock(Name.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);

        Account reference = new Account(nameDouble, emailDouble, phoneNumberDouble, photoDouble);
        Object other = new Object();

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if an Account and a null object are not the same.
     */
    @Test
    void ensureEmailInstanceDoesNotEqualNull() {
        // Arrange
        Name nameDouble = mock(Name.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);

        Account reference = new Account(nameDouble, emailDouble, phoneNumberDouble, photoDouble);

        // Act
        boolean result = reference.equals(null);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal Account objects are the same.
     */
    @Test
    public void ensureTwoAccountInstancesHashcodeAreTheSame() {
        // Arrange
        Name nameDouble = mock(Name.class);
        Email emailDouble = mock(Email.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        Account accountOne = new Account(nameDouble, emailDouble, phoneNumberDouble, photoDouble);
        Account accountTwo = new Account(nameDouble, emailDouble, phoneNumberDouble, photoDouble);

        // Act
        int accountOneHashCode = accountOne.hashCode();
        int accountTwoHashCode = accountTwo.hashCode();

        // Assert
        assertEquals(accountOneHashCode, accountTwoHashCode);
    }

    /**
     * Scenario 2: Two different Account objects are not the same.
     */
    @Test
    public void ensureTwoAccountInstancesHashcodeAreNotTheSame() {
        // Arrange
        Name nameDouble = mock(Name.class);
        Email emailDoubleOne = mock(Email.class);
        Email emailDoubleTwo = mock(Email.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        Account accountOne = new Account(nameDouble, emailDoubleOne, phoneNumberDouble, photoDouble);
        Account accountTwo = new Account(nameDouble, emailDoubleTwo, phoneNumberDouble, photoDouble);

        // Act
        int accountOneHashCode = accountOne.hashCode();
        int accountTwoHashCode = accountTwo.hashCode();

        // Assert
        assertNotEquals(accountOneHashCode, accountTwoHashCode);
    }

    /**
     * METHOD sameIdentityAs()
     * <br>
     * Scenario 1: Check if two instances of Account are equal if the value of their
     * email are the same.
     */
    @Test
    void ensureThatTwoAccountsAreTheSame() {
        // Arrange
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);

        Account account = new Account(name, email, phoneNumber, photo);
        Account accountTwo = new Account(name, email, phoneNumber, photo);

        // Act
        boolean result = account.sameIdentityAs(accountTwo);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of Account are not equal if the value of
     * email are not the same.
     */
    @Test
    void ensureThatTwoAccountsAreNotTheSame() {
        // Arrange
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);

        Email emailTwo = mock(Email.class);

        Account account = new Account(name, email, phoneNumber, photo);
        Account accountTwo = new Account(name, emailTwo, phoneNumber, photo);

        // Act
        boolean result = account.sameIdentityAs(accountTwo);

        //Assert
        assertFalse(result);
    }
}