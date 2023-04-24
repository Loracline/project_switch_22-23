package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

class AcceptanceCriteriaTest {
    /**
     * METHOD constructor
     * <p>
     * Scenario 1: verifies if an instance of AcceptanceCriteria is not created because the string passed as
     * argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsNull() {
        //Arrange
        String expected = "The acceptance criteria must not be null";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new AcceptanceCriteria(null));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if an instance of AcceptanceCriteria is not created because the string passed as
     * argument is empty.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsEmpty() {
        //Arrange
        String expected = "The acceptance criteria must not be empty";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new AcceptanceCriteria(""));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 3: verifies if an instance of AcceptanceCriteria is not created because the string passed as
     * argument is blank.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsBlank() {
        //Arrange
        String expected = "The acceptance criteria must not be blank";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new AcceptanceCriteria(" "));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD getAcceptanceCriteria()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the acceptanceCriteria attribute of the
     * AcceptanceCriteria value object.
     */
    @Test
    void ensureAcceptanceCriteriaIsRetrievedSuccessfully() {
        // Arrange
        AcceptanceCriteria acceptanceCriteria = new AcceptanceCriteria("Example acceptance criteria");
        String expected = "Example acceptance criteria".toLowerCase();

        // Act
        String result = acceptanceCriteria.getAcceptanceCriteria();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of AcceptanceCriteria are equal if the value of their attribute
     * acceptanceCriteria is the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoAcceptanceCriteriaInstancesHaveTheSameAcceptanceCriteriaValue() {
        //Arrange
        AcceptanceCriteria acceptanceCriteria = new AcceptanceCriteria("Example acceptance criteria");
        AcceptanceCriteria otherAcceptanceCriteria = new AcceptanceCriteria("Example acceptance criteria");
        //Act
        boolean result = acceptanceCriteria.sameValueAs(otherAcceptanceCriteria);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of AcceptanceCriteria are not equal if the value of their attribute
     * acceptanceCriteria is not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoAcceptanceCriteriaInstancesHaveDifferentAcceptanceCriteriaValues() {
        //Arrange
        AcceptanceCriteria acceptanceCriteria = new AcceptanceCriteria("One example of acceptance criteria");
        AcceptanceCriteria otherAcceptanceCriteria = new AcceptanceCriteria("Other example acceptance criteria");
        //Act
        boolean result = acceptanceCriteria.sameValueAs(otherAcceptanceCriteria);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameAcceptanceCriteriaEqualsItself() {
        // Arrange
        AcceptanceCriteria reference = new AcceptanceCriteria("Example acceptance criteria");
        AcceptanceCriteria other = reference;

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
        AcceptanceCriteria reference = new AcceptanceCriteria("Example acceptance criteria");
        AcceptanceCriteria other = new AcceptanceCriteria("Example acceptance criteria");

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
    void ensureTwoDifferentAcceptanceCriteriaInstancesAreNotTheSame() {
        // Arrange
        AcceptanceCriteria reference = new AcceptanceCriteria("One example of acceptance criteria");
        AcceptanceCriteria other = new AcceptanceCriteria("Other example of acceptance criteria");

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if an AcceptanceCriteria instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureAcceptanceCriteriaDoesNotEqualOtherTypeOfObject() {
        // Arrange
        AcceptanceCriteria reference = new AcceptanceCriteria("Example acceptance criteria");
        String other = "other object";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if an AcceptanceCriteria and a null object are not the same.
     */
    @Test
    void ensureAcceptanceCriteriaInstanceDoesNotEqualNull() {
        // Arrange
        AcceptanceCriteria reference = new AcceptanceCriteria("Example acceptance criteria");
        AcceptanceCriteria other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal AcceptanceCriteria objects are the same.
     */
    @Test
    public void ensureTwoAcceptanceCriteriaInstancesHashcodeAreTheSame() {
        // Arrange
        AcceptanceCriteria acceptanceCriteriaOne = new AcceptanceCriteria("Example acceptance criteria");
        AcceptanceCriteria acceptanceCriteriaTwo = new AcceptanceCriteria("Example acceptance criteria");

        // Act
        int acceptanceCriteriaOneHashCode = acceptanceCriteriaOne.hashCode();
        int acceptanceCriteriaTwoHashCode = acceptanceCriteriaTwo.hashCode();

        // Assert
        assertEquals(acceptanceCriteriaOneHashCode, acceptanceCriteriaTwoHashCode);
    }

    /**
     * Scenario 2: Two different AcceptanceCriteria objects are not the same.
     */
    @Test
    public void ensureTwoAcceptanceCriteriaInstancesHashcodeAreNotTheSame() {
        // Arrange
        AcceptanceCriteria acceptanceCriteriaOne = new AcceptanceCriteria("One example of acceptance criteria");
        AcceptanceCriteria acceptanceCriteriaThree = new AcceptanceCriteria("A different example of acceptance " +
                "criteria");

        // Act
        int acceptanceCriteriaOneHashCode = acceptanceCriteriaOne.hashCode();
        int acceptanceCriteriaThreeHashCode = acceptanceCriteriaThree.hashCode();

        // Assert
        assertNotEquals(acceptanceCriteriaOneHashCode, acceptanceCriteriaThreeHashCode);
    }

}