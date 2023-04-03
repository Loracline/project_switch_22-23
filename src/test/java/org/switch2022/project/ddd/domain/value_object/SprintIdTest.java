package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SprintIdTest {
    /**
     * METHOD constructor
     * <p>
     * Scenario 1: verifies if an instance of SprintId is not created because the string corresponding to the project code
     * passed as argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenProjectCodeIsNull() {
        //Arrange
        String expected = "The project code must not be null";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new SprintId(null, "S001"));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if an instance of SprintId is not created because the string corresponding to the sprint
     * number passed as argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenUsNumberIsNull() {
        //Arrange
        String expected = "The sprint number must not be null";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new SprintId("P001", null));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 3: verifies if an instance of SprintId is not created because the string corresponding to the project code
     * passed as argument is empty.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenProjectCodeIsEmpty() {
        //Arrange
        String expected = "The project code must not be empty";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new SprintId("", "US001"));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 4: verifies if an instance of SprintId is not created because the string corresponding to the sprint
     * number passed as argument is empty.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenUsNumberIsEmpty() {
        //Arrange
        String expected = "The sprint number must not be empty";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new SprintId("P001", ""));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 3: verifies if an instance of UsId is not created because the string corresponding to the project code
     * passed as argument is blank.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenProjectCodeIsBlank() {
        //Arrange
        String expected = "The project code must not be blank";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new SprintId(" ", "S001"));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 4: verifies if an instance of UsId is not created because the string corresponding to the user story
     * number passed as argument is blank.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenUsNumberIsBlank() {
        //Arrange
        String expected = "The sprint number must not be blank";

        //Act
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
                new SprintId("P001", " "));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD getUserStoryId()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the id attribute of the SprintId value object.
     */
    @Test
    void ensureUserStoryIdIsRetrievedSuccessfully() {
        // Arrange
        SprintId sprintId = new SprintId("P001", "S001");
        String expected = "P001_S001".toLowerCase();

        // Act
        String result = sprintId.getSprintId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of SprintId are equal if the value of their attribute sprint id is the
     * same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoSprintIdInstancesHaveTheSameIdValue() {
        //Arrange
        SprintId sprintId = new SprintId("P001", "S001");
        SprintId otherSprintId = new SprintId("P001", "S001");
        //Act
        boolean result = sprintId.sameValueAs(otherSprintId);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of SprintId are not equal if the value of their attribute sprint id is
     * not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoSprintIdInstancesHaveDifferentIdValues() {
        //Arrange
        SprintId sprintId = new SprintId("P001", "S001");
        SprintId otherSprintId = new SprintId("P002", "S001");
        //Act
        boolean result = sprintId.sameValueAs(otherSprintId);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameSprintIdEqualsItself() {
        // Arrange
        SprintId reference = new SprintId("P001", "S001");
        SprintId other = reference;

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
        SprintId reference = new SprintId("P001", "S001");
        SprintId other = new SprintId("P001", "S001");

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
    void ensureTwoDifferentSprintIdInstancesAreNotTheSame() {
        // Arrange
        SprintId reference = new SprintId("P001", "S001");
        SprintId other = new SprintId("P002", "S001");

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a SprintId instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureSprintIdDoesNotEqualOtherTypeOfObject() {
        // Arrange
        SprintId reference = new SprintId("P001", "S001");
        String other = "User";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a SprintId and a null object are not the same.
     */
    @Test
    void ensureSprintIdInstanceDoesNotEqualNull() {
        // Arrange
        SprintId reference = new SprintId("P001", "S001");
        SprintId other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal SprintId objects are the same.
     */
    @Test
    public void ensureTwoSprintIdInstancesHashcodeAreTheSame() {
        // Arrange
        SprintId sprintIdOne = new SprintId("P001", "S001");
        SprintId sprintIdTwo = new SprintId("P001", "S001");

        // Act
        int sprintIdOneHashCode = sprintIdOne.hashCode();
        int sprintIdTwoHashCode = sprintIdTwo.hashCode();

        // Assert
        assertEquals(sprintIdOneHashCode, sprintIdTwoHashCode);
    }

    /**
     * Scenario 2: Two different SprintId objects are not the same.
     */
    @Test
    public void ensureTwoSprintIdInstancesHashcodeAreNotTheSame() {
        // Arrange
        SprintId sprintIdOne = new SprintId("P001", "S001");
        SprintId sprintIdThree = new SprintId("P003", "S001");

        // Act
        int sprintIdOneHashCode = sprintIdOne.hashCode();
        int sprintIdThreeHashCode = sprintIdThree.hashCode();

        // Assert
        assertNotEquals(sprintIdOneHashCode, sprintIdThreeHashCode);
    }
}