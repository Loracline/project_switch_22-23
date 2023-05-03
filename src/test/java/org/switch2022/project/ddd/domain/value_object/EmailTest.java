package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    /**
     * METHOD getEmail()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the email attribute of the
     * Email value object.
     */
    @Test
    void ensureEmailIsRetrievedSuccessfully() {
        // Arrange
        String value = "john@portugalmail.pt";
        Email email = new Email(value);
        String expected = value;

        // Act
        String result = email.getEmail();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of Email are equal if the value of their attribute email is
     * the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoEmailInstancesHaveTheSameEmailValue() {
        //Arrange
        String value = "john@portugalmail.pt";
        Email email = new Email(value);
        Email otherEmail = new Email(value);

        //Act
        boolean result = email.sameValueAs(otherEmail);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of Email are not equal if the value of their attribute email is not the
     * same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoEmailInstancesHaveDifferentEmailValues() {
        //Arrange
        String value = "john@portugalmail.pt";
        String otherValue = "johnny@portugalmail.pt";
        Email email = new Email(value);
        Email otherEmail = new Email(otherValue);
        //Act
        boolean result = email.sameValueAs(otherEmail);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameEmailEqualsItself() {
        // Arrange
        String value = "john@portugalmail.pt";
        Email reference = new Email(value);
        Email other = reference;

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
        String value = "john@portugalmail.pt";
        Email reference = new Email(value);
        Email other = new Email(value);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: Verify that two objects with different emails are not equal.
     */
    @Test
    void ensureTwoDifferentEmailInstancesAreNotTheSame() {
        // Arrange
        String value = "john@portugalmail.pt";
        String otherValue = "johnny@portugalmail.pt";
        Email reference = new Email(value);
        Email other = new Email(otherValue);
        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if an Email instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureEmailDoesNotEqualOtherTypeOfObject() {
        // Arrange
        String value = "john@portugalmail.pt";
        Email reference = new Email(value);
        String other = "other object";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if an Email and a null object are not the same.
     */
    @Test
    void ensureEmailInstanceDoesNotEqualNull() {
        // Arrange
        String value = "john@portugalmail.pt";
        Email reference = new Email(value);

        // Act
        boolean result = reference.equals(null);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal Email objects are the same.
     */
    @Test
    public void ensureTwoEmailInstancesHashcodeAreTheSame() {
        // Arrange
        String value = "john@portugalmail.pt";
        Email emailOne = new Email(value);
        Email emailTwo = new Email(value);

        // Act
        int emailOneHashCode = emailOne.hashCode();
        int emailTwoHashCode = emailTwo.hashCode();

        // Assert
        assertEquals(emailOneHashCode, emailTwoHashCode);
    }

    /**
     * Scenario 2: Two different Email objects are not the same.
     */
    @Test
    public void ensureTwoEmailInstancesHashcodeAreNotTheSame() {
        // Arrange
        String value = "john@portugalmail.pt";
        String otherValue = "johnny@portugalmail.pt";
        Email emailOne = new Email(value);
        Email emailTwo = new Email(otherValue);

        // Act
        int emailOneHashCode = emailOne.hashCode();
        int emailTwoHashCode = emailTwo.hashCode();

        // Assert
        assertNotEquals(emailOneHashCode, emailTwoHashCode);
    }
}