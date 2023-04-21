package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerIdTest {
    /**
     * METHOD constructor
     * <p>
     * Scenario 1: verifies if an instance of CustomerId is not created because the number passed as argument is
     * null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsNull() {
        //Arrange
        String expected = "The customer number must not be null";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new CustomerId(null));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if an instance of CustomerId is not created because the number passed as argument is
     * negative.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsNegative() {
        //Arrange
        String expected = "The customer number must not be negative";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new CustomerId(-1));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD getCustomerId()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the customer id attribute of the
     * CustomerId value object.
     */
    @Test
    void ensureCustomerIdIsRetrievedSuccessfully() {
        // Arrange
        CustomerId customerId = new CustomerId(1);
        String expected = "C001".toLowerCase();

        // Act
        String result = customerId.getCustomerId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of CustomerId are equal if the value of their attribute customer id is the
     * same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoCustomerIdInstancesHaveTheSameNumberValue() {
        //Arrange
        CustomerId customerId = new CustomerId(1);
        CustomerId otherCustomerId = new CustomerId(1);
        //Act
        boolean result = customerId.sameValueAs(otherCustomerId);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of CustomerId are not equal if the value of their attribute
     * customer id is not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoCustomerIdInstancesHaveDifferentNumberValues() {
        //Arrange
        CustomerId customerId = new CustomerId(1);
        CustomerId otherCustomerId = new CustomerId(2);
        //Act
        boolean result = customerId.sameValueAs(otherCustomerId);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameCustomerIdEqualsItself() {
        // Arrange
        CustomerId reference = new CustomerId(1);
        CustomerId other = reference;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two objects with the same id are equal.
     */
    @Test
    void ensureTwoInstancesWithSameIdAreEqual() {
        // Arrange
        CustomerId reference = new CustomerId(1);
        CustomerId other = new CustomerId(1);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: Verify if two different objects of the same class are different from
     * each other.
     */
    @Test
    void ensureTwoDifferentCustomerIdInstancesAreNotTheSame() {
        // Arrange
        CustomerId reference = new CustomerId(1);
        CustomerId other = new CustomerId(2);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a CustomerId instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureCustomerIdDoesNotEqualOtherTypeOfObject() {
        // Arrange
        CustomerId reference = new CustomerId(1);
        String other = "User";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a CustomerId and a null object are not the same.
     */
    @Test
    void ensureCustomerIdInstanceDoesNotEqualNull() {
        // Arrange
        CustomerId reference = new CustomerId(1);
        CustomerId other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal CustomerId objects are the same.
     */
    @Test
    public void ensureTwoCustomerIdInstancesHashcodeAreTheSame() {
        // Arrange
        CustomerId customerIdOne = new CustomerId(1);
        CustomerId customerIdTwo = new CustomerId(1);

        // Act
        int customerIdOneHashCode = customerIdOne.hashCode();
        int customerIdTwoHashCode = customerIdTwo.hashCode();

        // Assert
        assertEquals(customerIdOneHashCode, customerIdTwoHashCode);
    }

    /**
     * Scenario 2: Two different CustomerId objects are not the same.
     */
    @Test
    public void ensureTwoCustomerIdInstancesHashcodeAreNotTheSame() {
        // Arrange
        CustomerId customerIdOne = new CustomerId(1);
        CustomerId customerIdThree = new CustomerId(3);

        // Act
        int customerIdOneHashCode = customerIdOne.hashCode();
        int customerIdThreeHashCode = customerIdThree.hashCode();

        // Assert
        assertNotEquals(customerIdOneHashCode, customerIdThreeHashCode);
    }

}