package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {
    /**
     * METHOD constructor
     * <p>
     * Scenario 1: verifies if an instance of Description is not created because the string passed as
     * argument is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsNull() {
        //Arrange
        String expected = "The project description must not be null";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new Description(null));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 2: verifies if an instance of Description is not created because the string passed as
     * argument is empty.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsEmpty() {
        //Arrange
        String expected = "The project description must not be empty";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new Description(""));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Scenario 3: verifies if an instance of Description is not created because the string passed as
     * argument is blank.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenStringIsBlank() {
        //Arrange
        String expected = "The project description must not be blank";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new Description(" "));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD getDescription()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the description attribute of the
     * Description value object.
     */
    @Test
    void ensureDescriptionIsRetrievedSuccessfully() {
        // Arrange
        Description name = new Description("Women's association");
        String expected = "Women's association".toLowerCase();

        // Act
        String result = name.getDescription();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of Description are equal if the value of their attribute description is
     * the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoDescriptionInstancesHaveTheSameDescriptionValue() {
        //Arrange
        Description description = new Description("Fighting for equality");
        Description otherDescription = new Description("Fighting for equality");
        //Act
        boolean result = description.sameValueAs(otherDescription);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of Description are not equal if the value of their attribute description is
     * not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoDescriptionInstancesHaveDifferentDescriptionValues() {
        //Arrange
        Description description = new Description("Fighting for equality");
        Description otherDescription = new Description("Women's rights are human rights");
        //Act
        boolean result = description.sameValueAs(otherDescription);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameDescriptionEqualsItself() {
        // Arrange
        Description reference = new Description("Fighting for equality");
        Description other = reference;

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
        Description reference = new Description("Fighting for equality");
        Description other = new Description("Fighting for equality");

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
    void ensureTwoDifferentDescriptionInstancesAreNotTheSame() {
        // Arrange
        Description reference = new Description("Fighting for equality");
        Description other = new Description("Women's rights are human rights");

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a Description instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureDescriptionDoesNotEqualOtherTypeOfObject() {
        // Arrange
        Description reference = new Description("Fighting for equality");
        String other = "other object";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a Description and a null object are not the same.
     */
    @Test
    void ensureDescriptionInstanceDoesNotEqualNull() {
        // Arrange
        Description reference = new Description("Fighting for equality");
        Description other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal Description objects are the same.
     */
    @Test
    public void ensureTwoDescriptionInstancesHashcodeAreTheSame() {
        // Arrange
        Description descriptionOne = new Description("Fighting for equality");
        Description descriptionTwo = new Description("Fighting for equality");

        // Act
        int descriptionOneHashCode = descriptionOne.hashCode();
        int descriptionTwoHashCode = descriptionTwo.hashCode();

        // Assert
        assertEquals(descriptionOneHashCode, descriptionTwoHashCode);
    }

    /**
     * Scenario 2: Two different Description objects are not the same.
     */
    @Test
    public void ensureTwoDescriptionInstancesHashcodeAreNotTheSame() {
        // Arrange
        Description descriptionOne = new Description("Fighting for equality");
        Description descriptionThree = new Description("Women's rights are humans rights");

        // Act
        int descriptionOneHashCode = descriptionOne.hashCode();
        int descriptionThreeHashCode = descriptionThree.hashCode();

        // Assert
        assertNotEquals(descriptionOneHashCode, descriptionThreeHashCode);
    }

}