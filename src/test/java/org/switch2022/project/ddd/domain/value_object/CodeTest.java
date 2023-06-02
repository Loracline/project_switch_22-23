package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

class CodeTest {

    /**
     * Scenario 1: verifies if an instance of Code is not created because the number passed as
     * argument is negative.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenNumberIsNegative() {
        //Arrange
        String expected = "The project number must not be negative";

        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new Code(-2));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD getCode()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the code attribute of the
     * Code value object.
     */
    @Test
    void ensureCodeIsRetrievedSuccessfully() {
        // Arrange
        Code code = new Code(1);
        String expected = "P001".toLowerCase();

        // Act
        String result = code.getCode();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of Code are equal if the value of their attribute code is
     * the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoCodeInstancesHaveTheSameCodeValue() {
        //Arrange
        Code code = new Code(1);
        Code otherCode = new Code(1);
        //Act
        boolean result = code.sameValueAs(otherCode);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of Code are not equal if the value of their attribute code is not the
     * same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoCodeInstancesHaveDifferentCodeValues() {
        //Arrange
        Code code = new Code(1);
        Code otherCode = new Code(2);
        //Act
        boolean result = code.sameValueAs(otherCode);
        //Assert
        assertFalse(result);
    }
    /**
     * Test case for comparing with a null object.
     * The sameValueAs method should return false when comparing with a null object.
     */
    @Test
    public void testSameValueAsWithNullObject() {
        Code code1 = new Code(1);
        assertFalse(code1.sameValueAs(null));
    }

    /**
     * Test case for comparing with the same object.
     * The sameValueAs method should return true when comparing with the same object.
     */
    @Test
    public void testSameValueAsWithSameObject() {
        Code code1 = new Code(1);
        assertTrue(code1.sameValueAs(code1));
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameCodeEqualsItself() {
        // Arrange
        Code reference = new Code(1);
        Code other = reference;

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
        Code reference = new Code(1);
        Code other = new Code(1);

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
    void ensureTwoDifferentCodeInstancesAreNotTheSame() {
        // Arrange
        Code reference = new Code(1);
        Code other = new Code(2);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a Code instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureCodeDoesNotEqualOtherTypeOfObject() {
        // Arrange
        Code reference = new Code(1);
        String other = "other object";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a Code and a null object are not the same.
     */
    @Test
    void ensureCodeInstanceDoesNotEqualNull() {
        // Arrange
        Code reference = new Code(1);
        Code other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal Code objects are the same.
     */
    @Test
    void ensureTwoCodeInstancesHashcodeAreTheSame() {
        // Arrange
        Code codeOne = new Code(1);
        Code codeTwo = new Code(1);

        // Act
        int codeOneHashCode = codeOne.hashCode();
        int codeTwoHashCode = codeTwo.hashCode();

        // Assert
        assertEquals(codeOneHashCode, codeTwoHashCode);
    }

    /**
     * Scenario 2: Two different Code objects are not the same.
     */
    @Test
    void ensureTwoCodeInstancesHashcodeAreNotTheSame() {
        // Arrange
        Code codeOne = new Code(1);
        Code codeThree = new Code(3);

        // Act
        int codeOneHashCode = codeOne.hashCode();
        int codeThreeHashCode = codeThree.hashCode();

        // Assert
        assertNotEquals(codeOneHashCode, codeThreeHashCode);
    }

    /**
     * METHOD getCodeFromString()
     */
    @DisplayName("From string to code")
    @Test
    void ensureCodeIsRetrievedSuccessfullyFromString() {
        // Arrange
        String stringOf_projectCode = "P001";
        int projectNumber = 1;
        Code expected = new Code(projectNumber);

        // Act
        Code result = Code.getCodeFromString(stringOf_projectCode);

        // Assert
        assertEquals(expected, result);
    }
}