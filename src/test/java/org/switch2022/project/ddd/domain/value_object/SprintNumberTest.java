package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SprintNumberTest {
    /**
     * METHOD constructor
     * <p>
     * Scenario 1: verifies if an instance of SprintNumber is not created because the number passed as argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsNull() {
        //Arrange
        String expected = "The sprint number must not be null";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new SprintNumber(null));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if an instance of SprintNumber is not created because the string number as argument is
     * negative.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsNegative() {
        //Arrange
        String expected = "The sprint number must not be negative";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new SprintNumber(-1));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD getSprintId()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the sprint id attribute of the
     * SprintNumber value object.
     */
    @Test
    void ensureSprintNumberIsRetrievedSuccessfully() {
        // Arrange
        SprintNumber sprintNumber = new SprintNumber(1);
        String expected = "S001".toLowerCase();

        // Act
        String result = sprintNumber.getSprintId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of SprintNumber are equal if the value of their attribute sprint id is
     * the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoSprintNumberInstancesHaveTheSameNumberValue() {
        //Arrange
        SprintNumber sprintNumber = new SprintNumber(1);
        SprintNumber otherSprintNumber = new SprintNumber(1);
        //Act
        boolean result = sprintNumber.sameValueAs(otherSprintNumber);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of SprintNumber are not equal if the value of their attribute sprint id is not
     * the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoSprintNumberInstancesHaveDifferentNumberValues() {
        //Arrange
        SprintNumber sprintNumber = new SprintNumber(1);
        SprintNumber otherSprintNumber = new SprintNumber(2);
        //Act
        boolean result = sprintNumber.sameValueAs(otherSprintNumber);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameSprintNumberEqualsItself() {
        // Arrange
        SprintNumber reference = new SprintNumber(1);
        SprintNumber other = reference;

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
        SprintNumber reference = new SprintNumber(1);
        SprintNumber other = new SprintNumber(1);

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
    void ensureTwoDifferentSprintNumberInstancesAreNotTheSame() {
        // Arrange
        SprintNumber reference = new SprintNumber(1);
        SprintNumber other = new SprintNumber(2);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a SprintNumber instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureSprintNumberDoesNotEqualOtherTypeOfObject() {
        // Arrange
        SprintNumber reference = new SprintNumber(1);
        String other = "User";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a SprintNumber and a null object are not the same.
     */
    @Test
    void ensureSprintNumberInstanceDoesNotEqualNull() {
        // Arrange
        SprintNumber reference = new SprintNumber(1);
        SprintNumber other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal SprintNumber objects are the same.
     */
    @Test
    public void ensureTwoSprintNumberInstancesHashcodeAreTheSame() {
        // Arrange
        SprintNumber sprintNumberOne = new SprintNumber(1);
        SprintNumber sprintNumberTwo = new SprintNumber(1);

        // Act
        int sprintIdOneHashCode = sprintNumberOne.hashCode();
        int sprintIdTwoHashCode = sprintNumberTwo.hashCode();

        // Assert
        assertEquals(sprintIdOneHashCode, sprintIdTwoHashCode);
    }

    /**
     * Scenario 2: Two different SprintNumber objects are not the same.
     */
    @Test
    public void ensureTwoSprintNumberInstancesHashcodeAreNotTheSame() {
        // Arrange
        SprintNumber sprintNumberOne = new SprintNumber(1);
        SprintNumber sprintNumberThree = new SprintNumber(3);

        // Act
        int sprintIdOneHashCode = sprintNumberOne.hashCode();
        int sprintIdThreeHashCode = sprintNumberThree.hashCode();

        // Assert
        assertNotEquals(sprintIdOneHashCode, sprintIdThreeHashCode);
    }

}