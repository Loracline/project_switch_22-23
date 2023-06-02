package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {

    /**
     * METHOD getPhoneNumber()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the phoneNumber attribute of the
     * PhoneNumber value object.
     */
    @Test
    void ensurePhoneNumberIsRetrievedSuccessfully() {
        // Arrange
        int value = 922321456;
        PhoneNumber phoneNumber = new PhoneNumber(value);
        int expected = value;

        // Act
        int result = phoneNumber.getPhoneNumber();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of PhoneNumber are equal if the value of their attribute phoneNumber is
     * the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoPhoneNumberInstancesHaveTheSamePhoneNumberValue() {
        //Arrange
        int value = 964454321;
        PhoneNumber phoneNumber = new PhoneNumber(value);
        PhoneNumber otherPhoneNumber = new PhoneNumber(value);
        //Act
        boolean result = phoneNumber.sameValueAs(otherPhoneNumber);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of PhoneNumber are not equal if the value of their attribute phoneNumber
     * is not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoPhoneNumberInstancesHaveDifferentPhoneNumberValues() {
        //Arrange
        int value = 964454321;
        int otherValue = 969857456;
        PhoneNumber phoneNumber = new PhoneNumber(value);
        PhoneNumber otherPhoneNumber = new PhoneNumber(otherValue);
        //Act
        boolean result = phoneNumber.sameValueAs(otherPhoneNumber);
        //Assert
        assertFalse(result);
    }
    /**
     * Test case for sameValueAs method.
     * Returns true for the same instance of PhoneNumber.
     */
    @Test
    public void testSameValueAs_ReturnsTrueForSameInstance() {
        PhoneNumber phoneNumber = new PhoneNumber(923456789);

        boolean result = phoneNumber.sameValueAs(phoneNumber);

        assertTrue(result);
    }

    /**
     * Test case for sameValueAs method.
     * Returns true for two instances with the same phone number.
     */
    @Test
    public void testSameValueAs_ReturnsTrueForEqualPhoneNumber() {
        PhoneNumber phoneNumber1 = new PhoneNumber(923456789);
        PhoneNumber phoneNumber2 = new PhoneNumber(923456789);

        boolean result = phoneNumber1.sameValueAs(phoneNumber2);

        assertTrue(result);
    }

    /**
     * Test case for sameValueAs method.
     * Returns false for null input.
     */
    @Test
    public void testSameValueAs_ReturnsFalseForNullInput() {
        PhoneNumber phoneNumber = new PhoneNumber(923456789);

        boolean result = phoneNumber.sameValueAs(null);

        assertFalse(result);
    }

    /**
     * Test case for sameValueAs method.
     * Returns false for two instances with different phone numbers.
     */
    @Test
    public void testSameValueAs_ReturnsFalseForDifferentPhoneNumber() {
        PhoneNumber phoneNumber1 = new PhoneNumber(923456789);
        PhoneNumber phoneNumber2 = new PhoneNumber(987654321);

        boolean result = phoneNumber1.sameValueAs(phoneNumber2);

        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSamePhoneNumberEqualsItself() {
        // Arrange
        int value = 964454321;
        PhoneNumber reference = new PhoneNumber(value);
        PhoneNumber other = reference;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two objects with the same phoneNumber are equal.
     */
    @Test
    void ensureTwoInstancesWithSamePhoneNumberAreEqual() {
        // Arrange
        int value = 911232456;
        PhoneNumber reference = new PhoneNumber(value);
        PhoneNumber other = new PhoneNumber(value);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: Verify that two objects with different phoneNumbers are not equal.
     */
    @Test
    void ensureTwoDifferentPhoneNumberInstancesAreNotTheSame() {
        // Arrange
        int value = 912123123;
        int otherValue = 912123124;
        PhoneNumber reference = new PhoneNumber(value);
        PhoneNumber other = new PhoneNumber(otherValue);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a PhoneNumber instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensurePhoneNumberDoesNotEqualOtherTypeOfObject() {
        // Arrange
        int value = 978654123;
        PhoneNumber reference = new PhoneNumber(value);
        String other = "other object";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a PhoneNumber and a null object are not the same.
     */
    @Test
    void ensurePhoneNumberInstanceDoesNotEqualNull() {
        // Arrange
        int value = 917586541;
        PhoneNumber reference = new PhoneNumber(value);

        // Act
        boolean result = reference.equals(null);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal PhoneNumber objects are the same.
     */
    @Test
    public void ensureTwoPhoneNumberInstancesHashcodeAreTheSame() {
        // Arrange
        int value = 912234789;
        PhoneNumber phoneNumberOne = new PhoneNumber(value);
        PhoneNumber phoneNumberTwo = new PhoneNumber(value);

        // Act
        int phoneNumberOneHashCode = phoneNumberOne.hashCode();
        int phoneNumberTwoHashCode = phoneNumberTwo.hashCode();

        // Assert
        assertEquals(phoneNumberOneHashCode, phoneNumberTwoHashCode);
    }

    /**
     * Scenario 2: Two different PhoneNumber objects are not the same.
     */
    @Test
    public void ensureTwoPhoneNumberInstancesHashcodeAreNotTheSame() {
        // Arrange
        int value = 935687459;
        int otherValue = 914254851;
        PhoneNumber phoneNumberOne = new PhoneNumber(value);
        PhoneNumber phoneNumberTwo = new PhoneNumber(otherValue);

        // Act
        int budgetOneHashCode = phoneNumberOne.hashCode();
        int budgetThreeHashCode = phoneNumberTwo.hashCode();

        // Assert
        assertNotEquals(budgetOneHashCode, budgetThreeHashCode);
    }
}