package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    /**
     * METHOD getName()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the name attribute of the
     * Name value object.
     */
    @Test
    void ensureNameIsRetrievedSuccessfully() {
        // Arrange
        Name name = new Name("Women's association");
        String expected = "Women's association".toLowerCase();

        // Act
        String result = name.getName();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of Name are equal if the value of their attribute name is
     * the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoNameInstancesHaveTheSameNameValue() {
        //Arrange
        Name name = new Name("Women's Association");
        Name otherName = new Name("Women's Association");
        //Act
        boolean result = name.sameValueAs(otherName);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of Name are not equal if the value of their attribute name is not the
     * same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoNameInstancesHaveDifferentNameValues() {
        //Arrange
        Name name = new Name("Women's Association");
        Name otherName = new Name("Girl Power Organization");
        //Act
        boolean result = name.sameValueAs(otherName);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameNameEqualsItself() {
        // Arrange
        Name reference = new Name("Women's Association");
        Name other = reference;

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
    void ensureTwoDifferentNameInstancesAreNotTheSame() {
        // Arrange
        Name reference = new Name("Women's Association");
        Name other = new Name("Female Empowerment Foundation");

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Verify if a Name instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureNameDoesNotEqualOtherTypeOfObject() {
        // Arrange
        Name reference = new Name("Women's Association");
        String other = "other object";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a Name and a null object are not the same.
     */
    @Test
    void ensureNameInstanceDoesNotEqualNull() {
        // Arrange
        Name reference = new Name("Women's Association");
        Name other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal Name objects are the same.
     */
    @Test
    public void ensureTwoNameInstancesHashcodeAreTheSame() {
        // Arrange
        Name nameOne = new Name("Women's Association");
        Name nameTwo = new Name("Women's Association");

        // Act
        int nameOneHashCode = nameOne.hashCode();
        int nameTwoHashCode = nameTwo.hashCode();

        // Assert
        assertEquals(nameOneHashCode, nameTwoHashCode);
    }

    /**
     * Scenario 2: Two different Name objects are not the same.
     */
    @Test
    public void ensureTwoNameInstancesHashcodeAreNotTheSame() {
        // Arrange
        Name nameOne = new Name("Women's Association");
        Name nameThree = new Name("Female Foundation");

        // Act
        int nameOneHashCode = nameOne.hashCode();
        int nameThreeHashCode = nameThree.hashCode();

        // Assert
        assertNotEquals(nameOneHashCode, nameThreeHashCode);
    }

}