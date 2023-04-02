package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

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
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
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
        IllegalArgumentException exception = assertThrowsExactly(IllegalArgumentException.class, () ->
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