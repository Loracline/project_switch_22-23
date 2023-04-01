package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsTextTest {
    /**
     * METHOD getUserStoryText()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the user story text attribute of the
     * UsText value object.
     */
    @Test
    void ensureUserStoryTextIsRetrievedSuccessfully() {
        // Arrange
        UsText usText = new UsText("I want to create a user story");
        String expected = "I want to create a user story".toLowerCase();

        // Act
        String result = usText.getUserStoryText();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of UsText are equal if the value of their attribute user story text is
     * the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoUsTextInstancesHaveTheSameTextValue() {
        //Arrange
        UsText usText = new UsText("I want to create a user story");
        UsText otherUsText = new UsText("I want to create a user story");
        //Act
        boolean result = usText.sameValueAs(otherUsText);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of UsNumber are not equal if the value of their attribute user story
     * text is not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoUsTextInstancesHaveDifferentTextValues() {
        //Arrange
        UsText usText = new UsText("I want to create a user story");
        UsText otherUsText = new UsText("I want to create a project");
        //Act
        boolean result = usText.sameValueAs(otherUsText);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameUsTextEqualsItself() {
        // Arrange
        UsText reference = new UsText("I want to create a user story");
        UsText other = reference;

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
    void ensureTwoDifferentUsTextInstancesAreNotTheSame() {
        // Arrange
        UsText reference = new UsText("I want to create a user story");
        UsText other = new UsText("I want to create a project");

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Verify if a UsText instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureUsTextDoesNotEqualOtherTypeOfObject() {
        // Arrange
        UsText reference = new UsText("I want to create a user story");
        String other = "User";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a UsText and a null object are not the same.
     */
    @Test
    void ensureUsTextInstanceDoesNotEqualNull() {
        // Arrange
        UsText reference = new UsText("I want to create a user story");
        UsText other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal UsText objects are the same.
     */
    @Test
    public void ensureTwoUsTextInstancesHashcodeAreTheSame() {
        // Arrange
        UsText usTextOne = new UsText("I want to create a user story");
        UsText usTextTwo = new UsText("I want to create a user story");

        // Act
        int usTextOneHashCode = usTextOne.hashCode();
        int usTextTwoHashCode = usTextTwo.hashCode();

        // Assert
        assertEquals(usTextOneHashCode, usTextTwoHashCode);
    }

    /**
     * Scenario 2: Two different UsText objects are not the same.
     */
    @Test
    public void ensureTwoUsTextInstancesHashcodeAreNotTheSame() {
        // Arrange
        UsText usTextOne = new UsText("I want to create a user story");
        UsText usTextThree = new UsText("I want to create a profile");

        // Act
        int usTextOneHashCode = usTextOne.hashCode();
        int usTextThreeHashCode = usTextThree.hashCode();

        // Assert
        assertNotEquals(usTextOneHashCode, usTextThreeHashCode);
    }

}