package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

class UsIdTest {
    /**
     * METHOD constructor
     * <p>
     * Scenario 1: verifies if an instance of UsId is not created because the string corresponding to the project code
     * passed as argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenProjectCodeIsNull() {
        //Arrange
        String expected = "The project code must not be null";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new UsId(null, "US001"));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if an instance of UsId is not created because the string corresponding to the user story
     * number passed as argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenUsNumberIsNull() {
        //Arrange
        String expected = "The user story number must not be null";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new UsId("P001", null));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 3: verifies if an instance of UsId is not created because the string corresponding to the project code
     * passed as argument is empty.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenProjectCodeIsEmpty() {
        //Arrange
        String expected = "The project code must not be empty";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new UsId("", "US001"));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 4: verifies if an instance of UsId is not created because the string corresponding to the user story
     * number passed as argument is empty.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenUsNumberIsEmpty() {
        //Arrange
        String expected = "The user story number must not be empty";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new UsId("P001", ""));

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
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new UsId(" ", "US001"));

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
        String expected = "The user story number must not be blank";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new UsId("P001", " "));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD getUserStoryId()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the id attribute of the UsId value object.
     */
    @Test
    void ensureUserStoryIdIsRetrievedSuccessfully() {
        // Arrange
        UsId usId = new UsId("P001", "US001");
        String expected = "P001_US001".toLowerCase();

        // Act
        String result = usId.getUserStoryId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of UsId are equal if the value of their attribute user story id is the
     * same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoUsIdInstancesHaveTheSameIdValue() {
        //Arrange
        UsId usId = new UsId("P001", "US001");
        UsId otherUsId = new UsId("P001", "US001");
        //Act
        boolean result = usId.sameValueAs(otherUsId);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of UsId are not equal if the value of their attribute user story id is
     * not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoUsIdInstancesHaveDifferentIdValues() {
        //Arrange
        UsId usId = new UsId("P001", "US001");
        UsId otherUsId = new UsId("P002", "US001");
        //Act
        boolean result = usId.sameValueAs(otherUsId);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameUsIdEqualsItself() {
        // Arrange
        UsId reference = new UsId("P001", "US001");
        UsId other = reference;

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
        UsId reference = new UsId("P001", "US001");
        UsId other = new UsId("P001", "US001");

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
    void ensureTwoDifferentUsIdInstancesAreNotTheSame() {
        // Arrange
        UsId reference = new UsId("P001", "US001");
        UsId other = new UsId("P002", "US001");

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a UsId instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureUsIdDoesNotEqualOtherTypeOfObject() {
        // Arrange
        UsId reference = new UsId("P001", "US001");
        String other = "User";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a UsId and a null object are not the same.
     */
    @Test
    void ensureUsIdInstanceDoesNotEqualNull() {
        // Arrange
        UsId reference = new UsId("P001", "US001");
        UsId other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal UsId objects are the same.
     */
    @Test
    public void ensureTwoUsIdInstancesHashcodeAreTheSame() {
        // Arrange
        UsId usIdOne = new UsId("P001", "US001");
        UsId usIdTwo = new UsId("P001", "US001");

        // Act
        int usIdOneHashCode = usIdOne.hashCode();
        int usIdTwoHashCode = usIdTwo.hashCode();

        // Assert
        assertEquals(usIdOneHashCode, usIdTwoHashCode);
    }

    /**
     * Scenario 2: Two different UsId objects are not the same.
     */
    @Test
    public void ensureTwoUsIdInstancesHashcodeAreNotTheSame() {
        // Arrange
        UsId usIdOne = new UsId("P001", "US001");
        UsId usIdThree = new UsId("P003", "US001");

        // Act
        int usIdOneHashCode = usIdOne.hashCode();
        int usIdThreeHashCode = usIdThree.hashCode();

        // Assert
        assertNotEquals(usIdOneHashCode, usIdThreeHashCode);
    }
}