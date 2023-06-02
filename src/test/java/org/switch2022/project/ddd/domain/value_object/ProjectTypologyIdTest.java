package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTypologyIdTest {
    /**
     * METHOD constructor
     * <p>
     * Scenario 1: verifies if an instance of ProjectTypologyId is not created because the number passed as argument is
     * null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsNull() {
        //Arrange
        String expected = "The project typology number must not be null";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new ProjectTypologyId(null));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if an instance of ProjectTypologyId is not created because the number passed as argument is
     * negative.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsNegative() {
        //Arrange
        String expected = "The project typology number must not be negative";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new ProjectTypologyId(-1));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD getProjectTypologyId()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the typology id attribute of the
     * ProjectTypologyId value object.
     */
    @Test
    void ensureProjectTypologyIdIsRetrievedSuccessfully() {
        // Arrange
        ProjectTypologyId typologyId = new ProjectTypologyId(1);
        String expected = "PT001".toLowerCase();

        // Act
        String result = typologyId.getProjectTypologyId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of ProjectTypologyId are equal if the value of their attribute TYPOLOGY id
     * is the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoProjectTypologyIdInstancesHaveTheSameNumberValue() {
        //Arrange
        ProjectTypologyId typologyId = new ProjectTypologyId(1);
        ProjectTypologyId otherTypologyId = new ProjectTypologyId(1);
        //Act
        boolean result =typologyId.sameValueAs(otherTypologyId);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of ProjectTypologyId are not equal if the value of their attribute
     * typology id is not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoProjectTypologyIdInstancesHaveDifferentNumberValues() {
        //Arrange
        ProjectTypologyId typologyId = new ProjectTypologyId(1);
        ProjectTypologyId otherTypologyId = new ProjectTypologyId(2);
        //Act
        boolean result = typologyId.sameValueAs(otherTypologyId);
        //Assert
        assertFalse(result);
    }
    /**
     * Test case for sameValueAs method.
     * Returns true for the same instance of ProjectTypologyId.
     */
    @Test
    public void testSameValueAs_ReturnsTrueForSameInstance() {
        ProjectTypologyId typologyId = new ProjectTypologyId(1);

        boolean result = typologyId.sameValueAs(typologyId);

        assertTrue(result);
    }

    /**
     * Test case for sameValueAs method.
     * Returns true for two instances with the same project typology ID.
     */
    @Test
    public void testSameValueAs_ReturnsTrueForEqualProjectTypologyId() {
        ProjectTypologyId typologyId1 = new ProjectTypologyId(1);
        ProjectTypologyId typologyId2 = new ProjectTypologyId(1);

        boolean result = typologyId1.sameValueAs(typologyId2);

        assertTrue(result);
    }

    /**
     * Test case for sameValueAs method.
     * Returns false for null input.
     */
    @Test
    public void testSameValueAs_ReturnsFalseForNullInput() {
        ProjectTypologyId typologyId = new ProjectTypologyId(1);

        boolean result = typologyId.sameValueAs(null);

        assertFalse(result);
    }

    /**
     * Test case for sameValueAs method.
     * Returns false for two instances with different project typology IDs.
     */
    @Test
    public void testSameValueAs_ReturnsFalseForDifferentProjectTypologyId() {
        ProjectTypologyId typologyId1 = new ProjectTypologyId(1);
        ProjectTypologyId typologyId2 = new ProjectTypologyId(2);

        boolean result = typologyId1.sameValueAs(typologyId2);

        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameProjectTypologyIdEqualsItself() {
        // Arrange
        ProjectTypologyId reference = new ProjectTypologyId(1);
        ProjectTypologyId other = reference;

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
        ProjectTypologyId reference = new ProjectTypologyId(1);
        ProjectTypologyId other = new ProjectTypologyId(1);

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
    void ensureTwoDifferentProjectTypologyIdInstancesAreNotTheSame() {
        // Arrange
        ProjectTypologyId reference = new ProjectTypologyId(1);
        ProjectTypologyId other = new ProjectTypologyId(2);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a ProjectTypologyId instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureProjectTypologyIdDoesNotEqualOtherTypeOfObject() {
        // Arrange
        ProjectTypologyId reference = new ProjectTypologyId(1);
        String other = "User";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a ProjectTypologyId and a null object are not the same.
     */
    @Test
    void ensureProjectTypologyIdInstanceDoesNotEqualNull() {
        // Arrange
        ProjectTypologyId reference = new ProjectTypologyId(1);
        ProjectTypologyId other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal ProjectTypologyId objects are the same.
     */
    @Test
    public void ensureTwoProjectTypologyIdInstancesHashcodeAreTheSame() {
        // Arrange
        ProjectTypologyId typologyIdOne = new ProjectTypologyId(1);
        ProjectTypologyId typologyIdTwo = new ProjectTypologyId(1);

        // Act
        int typologyIdOneHashCode = typologyIdOne.hashCode();
        int typologyIdTwoHashCode = typologyIdTwo.hashCode();

        // Assert
        assertEquals(typologyIdOneHashCode, typologyIdTwoHashCode);
    }

    /**
     * Scenario 2: Two different ProjectTypologyId objects are not the same.
     */
    @Test
    public void ensureTwoProjectTypologyIdInstancesHashcodeAreNotTheSame() {
        // Arrange
        ProjectTypologyId typologyIdOne = new ProjectTypologyId(1);
        ProjectTypologyId typologyIdThree = new ProjectTypologyId(3);

        // Act
        int typologyIdOneHashCode = typologyIdOne.hashCode();
        int typologyIdThreeHashCode = typologyIdThree.hashCode();

        // Assert
        assertNotEquals(typologyIdOneHashCode, typologyIdThreeHashCode);
    }

}