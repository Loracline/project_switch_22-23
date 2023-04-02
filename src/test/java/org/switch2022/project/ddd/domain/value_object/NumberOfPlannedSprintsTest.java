package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class NumberOfPlannedSprintsTest {
    /**
     * METHOD constructor
     * <p>
     * Scenario 1: verifies if an instance of NumberOfPlannedSprints is not created because the number passed as
     * argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenNumberIsNull() {
        //Arrange
        Number number = null;

        String expected = "The number of planned sprints must not be null";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new NumberOfPlannedSprints(number));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD constructor
     * <p>
     * Scenario 1: verifies if an instance of NumberOfPlannedSprints is not created because the number passed as
     * argument is negative.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenNumberIsNegative() {
        //Arrange
        Number number = -1;

        String expected = "The number of planned sprints must not be negative";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new NumberOfPlannedSprints(number));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD getNumberOfPlannedSprints()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the numberOfPlannedSprints attribute of
     * the NumberOfPlannedSprints value object.
     */
    @Test
    void ensureNumberOfPlannedSprintsIsRetrievedSuccessfully() {
        // Arrange
        int value = 30;
        NumberOfPlannedSprints numberOfPlannedSprints = new NumberOfPlannedSprints(value);
        int expected = value;

        // Act
        int result = numberOfPlannedSprints.getNumberOfPlannedSprints();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of NumberOfPlannedSprints are equal if the value of their attribute
     * numberOfPlannedSprints is the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoNumberOfPlannedSprintsInstancesHaveTheSameNumberOfPlannedSprintsValue() {
        //Arrange
        int value = 30;
        NumberOfPlannedSprints numberOfPlannedSprints = new NumberOfPlannedSprints(value);
        NumberOfPlannedSprints otherNumberOfPlannedSprints = new NumberOfPlannedSprints(value);
        //Act
        boolean result = numberOfPlannedSprints.sameValueAs(otherNumberOfPlannedSprints);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of NumberOfPlannedSprints are not equal if the value of their attribute
     * numberOfPlannedSprints is not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoNumberOfPlannedSprintsInstancesHaveDifferentNumberOfPlannedSprintsValues() {
        //Arrange
        int value = 30;
        int otherValue = 50;
        NumberOfPlannedSprints numberOfPlannedSprints = new NumberOfPlannedSprints(value);
        NumberOfPlannedSprints otherNumberOfPlannedSprints = new NumberOfPlannedSprints(otherValue);
        //Act
        boolean result = numberOfPlannedSprints.sameValueAs(otherNumberOfPlannedSprints);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameNumberOfPlannedSprintsEqualsItself() {
        // Arrange
        int value = 30;
        NumberOfPlannedSprints reference = new NumberOfPlannedSprints(value);
        NumberOfPlannedSprints other = reference;

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
        int value = 30;
        NumberOfPlannedSprints reference = new NumberOfPlannedSprints(value);
        NumberOfPlannedSprints other = new NumberOfPlannedSprints(value);

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
    void ensureTwoDifferentNumberOfPlannedSprintsInstancesAreNotTheSame() {
        // Arrange
        int value = 30;
        int otherValue = 50;
        NumberOfPlannedSprints reference = new NumberOfPlannedSprints(value);
        NumberOfPlannedSprints other = new NumberOfPlannedSprints(otherValue);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a NumberOfPlannedSprints instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureNumberOfPlannedSprintsDoesNotEqualOtherTypeOfObject() {
        // Arrange
        int value = 30;
        NumberOfPlannedSprints reference = new NumberOfPlannedSprints(value);
        String other = "other object";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a NumberOfPlannedSprints and a null object are not the same.
     */
    @Test
    void ensureNumberOfPlannedSprintsInstanceDoesNotEqualNull() {
        // Arrange
        int value = 30;
        NumberOfPlannedSprints reference = new NumberOfPlannedSprints(value);
        NumberOfPlannedSprints other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal NumberOfPlannedSprints objects are the same.
     */
    @Test
    public void ensureTwoNumberOfPlannedSprintsInstancesHashcodeAreTheSame() {
        // Arrange
        int value = 30;
        NumberOfPlannedSprints numberOfPlannedSprintsOne = new NumberOfPlannedSprints(value);
        NumberOfPlannedSprints numberOfPlannedSprintsTwo = new NumberOfPlannedSprints(value);

        // Act
        int numberOfPlannedSprintsOneHashCode = numberOfPlannedSprintsOne.hashCode();
        int numberOfPlannedSprintsTwoHashCode = numberOfPlannedSprintsTwo.hashCode();

        // Assert
        assertEquals(numberOfPlannedSprintsOneHashCode, numberOfPlannedSprintsTwoHashCode);
    }

    /**
     * Scenario 2: Two different NumberOfPlannedSprints objects are not the same.
     */
    @Test
    public void ensureTwoNumberOfPlannedSprintsInstancesHashcodeAreNotTheSame() {
        // Arrange
        int value = 30;
        int otherValue = 50;
        NumberOfPlannedSprints numberOfPlannedSprintsOne = new NumberOfPlannedSprints(value);
        NumberOfPlannedSprints numberOfPlannedSprintsThree = new NumberOfPlannedSprints(otherValue);

        // Act
        int numberOfPlannedSprintsOneHashCode = numberOfPlannedSprintsOne.hashCode();
        int numberOfPlannedSprintsThreeHashCode = numberOfPlannedSprintsThree.hashCode();

        // Assert
        assertNotEquals(numberOfPlannedSprintsOneHashCode, numberOfPlannedSprintsThreeHashCode);
    }

}