package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorIdTest {
    /**
     * METHOD constructor
     * <p>
     * Scenario 1: verifies if an instance of BusinessSectorId is not created because the number passed as argument is
     * null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsNull() {
        //Arrange
        String expected = "The business sector number must not be null";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new BusinessSectorId(null));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if an instance of BusinessSectorId is not created because the number passed as argument is
     * negative.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsNegative() {
        //Arrange
        String expected = "The business sector number must not be negative";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new BusinessSectorId(-1));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD getBusinessSectorId()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the business sector id attribute of the
     * BusinessSectorId value object.
     */
    @Test
    void ensureBusinessSectorIdIsRetrievedSuccessfully() {
        // Arrange
        BusinessSectorId businessSectorId = new BusinessSectorId(1);
        String expected = "BS001".toLowerCase();

        // Act
        String result = businessSectorId.getBusinessSectorId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of BusinessSectorId are equal if the value of their attribute business
     * sector id is the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoBusinessSectorIdInstancesHaveTheSameNumberValue() {
        //Arrange
        BusinessSectorId businessSectorId = new BusinessSectorId(1);
        BusinessSectorId otherBusinessSectorId = new BusinessSectorId(1);
        //Act
        boolean result = businessSectorId.sameValueAs(otherBusinessSectorId);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of BusinessSectorId are not equal if the value of their attribute
     * business sector id is not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoBusinessSectorIdInstancesHaveDifferentNumberValues() {
        //Arrange
        BusinessSectorId businessSectorId = new BusinessSectorId(1);
        BusinessSectorId otherBusinessSectorId = new BusinessSectorId(2);
        //Act
        boolean result = businessSectorId.sameValueAs(otherBusinessSectorId);
        //Assert
        assertFalse(result);
    }
    /**
     * Test case for sameValueAs method.
     * Returns true for the same instance of BusinessSectorId.
     */
    @Test
    public void testSameValueAs_ReturnsTrueForSameInstance() {
        BusinessSectorId businessSectorId = new BusinessSectorId(1);

        boolean result = businessSectorId.sameValueAs(businessSectorId);

        assertTrue(result);
    }

    /**
     * Test case for sameValueAs method.
     * Returns true for two instances with the same business sector ID.
     */
    @Test
    public void testSameValueAs_ReturnsTrueForEqualBusinessSectorId() {
        BusinessSectorId businessSectorId1 = new BusinessSectorId(1);
        BusinessSectorId businessSectorId2 = new BusinessSectorId(1);

        boolean result = businessSectorId1.sameValueAs(businessSectorId2);

        assertTrue(result);
    }

    /**
     * Test case for sameValueAs method.
     * Returns false for null input.
     */
    @Test
    public void testSameValueAs_ReturnsFalseForNullInput() {
        BusinessSectorId businessSectorId = new BusinessSectorId(1);

        boolean result = businessSectorId.sameValueAs(null);

        assertFalse(result);
    }

    /**
     * Test case for sameValueAs method.
     * Returns false for two instances with different business sector IDs.
     */
    @Test
    public void testSameValueAs_ReturnsFalseForDifferentBusinessSectorId() {
        BusinessSectorId businessSectorId1 = new BusinessSectorId(1);
        BusinessSectorId businessSectorId2 = new BusinessSectorId(2);

        boolean result = businessSectorId1.sameValueAs(businessSectorId2);

        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameBusinessSectorIdEqualsItself() {
        // Arrange
        BusinessSectorId reference = new BusinessSectorId(1);
        BusinessSectorId other = reference;

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
        BusinessSectorId reference = new BusinessSectorId(1);
        BusinessSectorId other = new BusinessSectorId(1);

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
    void ensureTwoDifferentBusinessSectorIdInstancesAreNotTheSame() {
        // Arrange
        BusinessSectorId reference = new BusinessSectorId(1);
        BusinessSectorId other = new BusinessSectorId(2);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a BusinessSectorId instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureBusinessSectorIdDoesNotEqualOtherTypeOfObject() {
        // Arrange
        BusinessSectorId reference = new BusinessSectorId(1);
        String other = "User";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a BusinessSectorId and a null object are not the same.
     */
    @Test
    void ensureBusinessSectorIdInstanceDoesNotEqualNull() {
        // Arrange
        BusinessSectorId reference = new BusinessSectorId(1);
        BusinessSectorId other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal BusinessSectorId objects are the same.
     */
    @Test
    public void ensureTwoBusinessSectorIdInstancesHashcodeAreTheSame() {
        // Arrange
        BusinessSectorId businessSectorIdOne = new BusinessSectorId(1);
        BusinessSectorId businessSectorIdTwo = new BusinessSectorId(1);

        // Act
        int businessSectorIdOneHashCode = businessSectorIdOne.hashCode();
        int businessSectorIdTwoHashCode = businessSectorIdTwo.hashCode();

        // Assert
        assertEquals(businessSectorIdOneHashCode, businessSectorIdTwoHashCode);
    }

    /**
     * Scenario 2: Two different BusinessSectorId objects are not the same.
     */
    @Test
    public void ensureTwoBusinessSectorIdInstancesHashcodeAreNotTheSame() {
        // Arrange
        BusinessSectorId businessSectorIdOne = new BusinessSectorId(1);
        BusinessSectorId businessSectorIdThree = new BusinessSectorId(3);

        // Act
        int businessSectorIdOneHashCode = businessSectorIdOne.hashCode();
        int businessSectorIdThreeHashCode = businessSectorIdThree.hashCode();

        // Assert
        assertNotEquals(businessSectorIdOneHashCode, businessSectorIdThreeHashCode);
    }

}