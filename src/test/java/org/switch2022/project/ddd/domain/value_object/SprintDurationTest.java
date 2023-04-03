package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SprintDurationTest {
    /**
     * METHOD constructor
     * <p>
     * Scenario 1: verifies if an instance of SprintDuration is not created because the number passed as
     * argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenNumberIsNull() {
        //Arrange
        Number number = null;

        String expected = "The sprint duration must not be null";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new SprintDuration(number));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD constructor
     * <p>
     * Scenario 1: verifies if an instance of SprintDuration is not created because the number passed as
     * argument is negative.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenNumberIsNegative() {
        //Arrange
        Number number = -1;

        String expected = "The sprint duration must be between 0 and 4";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new SprintDuration(number));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD getSprintDuration()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the sprintDuration attribute of
     * the SprintDuration value object.
     */
    @Test
    void ensureSprintDurationIsRetrievedSuccessfully() {
        // Arrange
        int value = 2;
        SprintDuration sprintDuration = new SprintDuration(value);
        int expected = value;

        // Act
        int result = sprintDuration.getSprintDuration();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of SprintDuration are equal if the value of their attribute
     * sprintDuration is the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoSprintDurationInstancesHaveTheSameSprintDurationValue() {
        //Arrange
        int value = 3;
        SprintDuration sprintDuration = new SprintDuration(value);
        SprintDuration otherSprintDuration = new SprintDuration(value);
        //Act
        boolean result = sprintDuration.sameValueAs(otherSprintDuration);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of SprintDuration are not equal if the value of their attribute
     * sprintDuration is not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoSprintDurationInstancesHaveDifferentSprintDurationValues() {
        //Arrange
        int value = 3;
        int otherValue = 4;
        SprintDuration sprintDuration = new SprintDuration(value);
        SprintDuration otherSprintDuration = new SprintDuration(otherValue);
        //Act
        boolean result = sprintDuration.sameValueAs(otherSprintDuration);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameSprintDurationEqualsItself() {
        // Arrange
        int value = 3;
        SprintDuration reference = new SprintDuration(value);
        SprintDuration other = reference;

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
        int value = 3;
        SprintDuration reference = new SprintDuration(value);
        SprintDuration other = new SprintDuration(value);

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
    void ensureTwoDifferentSprintDurationInstancesAreNotTheSame() {
        // Arrange
        int value = 3;
        int otherValue = 4;
        SprintDuration reference = new SprintDuration(value);
        SprintDuration other = new SprintDuration(otherValue);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a SprintDuration instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureSprintDurationDoesNotEqualOtherTypeOfObject() {
        // Arrange
        int value = 3;
        SprintDuration reference = new SprintDuration(value);
        String other = "other object";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a SprintDuration and a null object are not the same.
     */
    @Test
    void ensureSprintDurationInstanceDoesNotEqualNull() {
        // Arrange
        int value = 3;
        SprintDuration reference = new SprintDuration(value);
        SprintDuration other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal SprintDuration objects are the same.
     */
    @Test
    public void ensureTwoSprintDurationInstancesHashcodeAreTheSame() {
        // Arrange
        int value =3;
        SprintDuration sprintDurationOne = new SprintDuration(value);
        SprintDuration sprintDurationTwo = new SprintDuration(value);

        // Act
        int sprintDurationOneHashCode = sprintDurationOne.hashCode();
        int sprintDurationTwoHashCode = sprintDurationTwo.hashCode();

        // Assert
        assertEquals(sprintDurationOneHashCode, sprintDurationTwoHashCode);
    }

    /**
     * Scenario 2: Two different SprintDuration objects are not the same.
     */
    @Test
    public void ensureTwoSprintDurationInstancesHashcodeAreNotTheSame() {
        // Arrange
        int value = 3;
        int otherValue = 1;
        SprintDuration sprintDurationOne = new SprintDuration(value);
        SprintDuration sprintDurationThree = new SprintDuration(otherValue);

        // Act
        int sprintDurationOneHashCode = sprintDurationOne.hashCode();
        int sprintDurationThreeHashCode = sprintDurationThree.hashCode();

        // Assert
        assertNotEquals(sprintDurationOneHashCode, sprintDurationThreeHashCode);
    }

}