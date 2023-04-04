package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryDtoTest {
        /*
      METHOD equals()
     */

    /**
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameUserStoryDtoEqualsItself() {
        // Arrange
        UserStoryDto reference = new UserStoryDto("US001", "I want to create a profile",
                "Planned");
        UserStoryDto other = reference;
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify if two objects of the same class are different from
     * each other.
     */
    @Test
    void ensureTwoUserStoryDtosAreNotTheSame() {
        // Arrange
        UserStoryDto reference = new UserStoryDto("US001", "I want to create a profile",
                "Planned");

        UserStoryDto other = new UserStoryDto("US002", "I want to create a profile",
                "Planned");

        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verify if two objects from different types are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureUserStoryDtoDoesNotEqualOtherTypeOfObject() {
        // Arrange
        UserStoryDto reference = new UserStoryDto("US001", "I want to create a profile",
                "Planned");
        String other = "User";
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Verify if a UserStoryDto object and a null object are not the same.
     */
    @Test
    void ensureUserStoryDtoDoesNotEqualNull() {
        // Arrange
        UserStoryDto reference = new UserStoryDto("US001", "I want to create a profile",
                "Planned");
        UserStoryDto other = null;
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }


    /*
      METHOD hashCode()
     */

    /**
     * Scenario 1: Two UserStoryDto objects are the same.
     */
    @Test
    public void ensureTwoUserStoryDtosHashcodeAreTheSame() {
        // Arrange
        UserStoryDto userStoryDtoOne = new UserStoryDto("US001",
                "I want to create a profile",
                "Planned");
        UserStoryDto userStoryDtoTwo = new UserStoryDto("US001",
                "I want to create a profile",
                "Planned");

        // Act
        int userStoryDtoOneHashCode = userStoryDtoOne.hashCode();
        int userStoryDtoTwoHashCode = userStoryDtoTwo.hashCode();

        // Assert
        assertEquals(userStoryDtoOneHashCode, userStoryDtoTwoHashCode);
    }

    /**
     * Scenario 2: Two UserStoryDto objects are not the same.
     */
    @Test
    public void ensureTwoUserStoryDtosHashcodeAreNotTheSame() {
        // Arrange
        UserStoryDto userStoryDtoOne = new UserStoryDto("US001",
                "I want to create a profile",
                "Planned");
        UserStoryDto userStoryDtoThree = new UserStoryDto("US002", "I want to create a " +
                "profile",
                "Planned");

        // Act
        int userStoryDtoOneHashCode = userStoryDtoOne.hashCode();
        int userStoryDtoThreeHashCode = userStoryDtoThree.hashCode();

        // Assert
        assertNotEquals(userStoryDtoOneHashCode, userStoryDtoThreeHashCode);
    }
}
