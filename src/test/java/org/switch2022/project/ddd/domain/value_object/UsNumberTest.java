package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

class UsNumberTest {
    /**
     * METHOD constructor
     * <p>
     * Scenario 1: verifies if an instance of UsNumber is not created because the string passed as argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsNull() {
        //Arrange
        String expected = "The user story number must not be null";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new UsNumber(null));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if an instance of UsNumber is not created because the string passed as argument is empty.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsEmpty() {
        //Arrange
        String expected = "The user story number must not be empty";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new UsNumber(""));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 3: verifies if an instance of UsNumber is not created because the string passed as argument is empty.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsBlank() {
        //Arrange
        String expected = "The user story number must not be blank";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new UsNumber(" "));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD getUserStoryNumber()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the user story number attribute of the
     * UsNumber value object.
     */
    @Test
    void ensureUserStoryNumberIsRetrievedSuccessfully() {
        // Arrange
        UsNumber usNumber = new UsNumber("US001");
        String expected = "US001".toLowerCase();

        // Act
        String result = usNumber.getUserStoryNumber();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of UsNumber are equal if the value of their attribute user story number is
     * the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoUsNumberInstancesHaveTheSameNumberValue() {
        //Arrange
        UsNumber usNumber = new UsNumber("US001");
        UsNumber otherUsNumber = new UsNumber("US001");
        //Act
        boolean result = usNumber.sameValueAs(otherUsNumber);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of UsNumber are not equal if the value of their attribute user story
     * number is not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoUsNumberInstancesHaveDifferentNumberValues() {
        //Arrange
        UsNumber usNumber = new UsNumber("US001");
        UsNumber otherUsNumber = new UsNumber("US002");
        //Act
        boolean result = usNumber.sameValueAs(otherUsNumber);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameUsNumberEqualsItself() {
        // Arrange
        UsNumber reference = new UsNumber("US001");
        UsNumber other = reference;

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
        UsNumber reference = new UsNumber("US001");
        UsNumber other = new UsNumber("US001");

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
    void ensureTwoDifferentUsNumberInstancesAreNotTheSame() {
        // Arrange
        UsNumber reference = new UsNumber("US001");
        UsNumber other = new UsNumber("US002");

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a UsNumber instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureUsNumberDoesNotEqualOtherTypeOfObject() {
        // Arrange
        UsNumber reference = new UsNumber("US001");
        String other = "User";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a UsNumber and a null object are not the same.
     */
    @Test
    void ensureUsNumberInstanceDoesNotEqualNull() {
        // Arrange
        UsNumber reference = new UsNumber("US001");
        UsNumber other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal UsNumber objects are the same.
     */
    @Test
    public void ensureTwoUsNumberInstancesHashcodeAreTheSame() {
        // Arrange
        UsNumber usNumberOne = new UsNumber("US001");
        UsNumber usNumberTwo = new UsNumber("US001");

        // Act
        int usNumberOneHashCode = usNumberOne.hashCode();
        int usNumberTwoHashCode = usNumberTwo.hashCode();

        // Assert
        assertEquals(usNumberOneHashCode, usNumberTwoHashCode);
    }

    /**
     * Scenario 2: Two different UsNumber objects are not the same.
     */
    @Test
    public void ensureTwoUsNumberInstancesHashcodeAreNotTheSame() {
        // Arrange
        UsNumber usNumberOne = new UsNumber("US001");
        UsNumber usNumberThree = new UsNumber("US003");

        // Act
        int usNumberOneHashCode = usNumberOne.hashCode();
        int usNumberThreeHashCode = usNumberThree.hashCode();

        // Assert
        assertNotEquals(usNumberOneHashCode, usNumberThreeHashCode);
    }

}