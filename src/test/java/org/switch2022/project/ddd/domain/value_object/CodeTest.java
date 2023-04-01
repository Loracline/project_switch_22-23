package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeTest {
    /**
     * METHOD getCode()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the code attribute of the
     * Code value object.
     */
    @Test
    void ensureCodeIsRetrievedSuccessfully() {
        // Arrange
        Code code = new Code("P001");
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
        Code code = new Code("P001");
        Code otherCode = new Code("P001");
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
        Code code = new Code("P001");
        Code otherCode = new Code("P002");
        //Act
        boolean result = code.sameValueAs(otherCode);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameCodeEqualsItself() {
        // Arrange
        Code reference = new Code("P001");
        Code other = reference;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify if two different objects of the same class are different from
     * each other.
     */
    @Test
    void ensureTwoDifferentCodeInstancesAreNotTheSame() {
        // Arrange
        Code reference = new Code("P001");
        Code other = new Code("P002");

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Verify if a Code instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureCodeDoesNotEqualOtherTypeOfObject() {
        // Arrange
        Code reference = new Code("P001");
        String other = "other object";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a Code and a null object are not the same.
     */
    @Test
    void ensureCodeInstanceDoesNotEqualNull() {
        // Arrange
        Code reference = new Code("P001");
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
    public void ensureTwoCodeInstancesHashcodeAreTheSame() {
        // Arrange
        Code codeOne = new Code("P001");
        Code codeTwo = new Code("P001");

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
    public void ensureTwoCodeInstancesHashcodeAreNotTheSame() {
        // Arrange
        Code codeOne = new Code("P001");
        Code codeThree = new Code("P003");

        // Act
        int codeOneHashCode = codeOne.hashCode();
        int codeThreeHashCode = codeThree.hashCode();

        // Assert
        assertNotEquals(codeOneHashCode, codeThreeHashCode);
    }

}