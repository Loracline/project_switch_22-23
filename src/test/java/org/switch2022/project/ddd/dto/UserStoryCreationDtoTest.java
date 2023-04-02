package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserStoryCreationDtoTest {

    /**
     * Scenario 1: Test to ensure the same object equals itself.
     */
    @Test
    void ensureSameUserStoryCreationDtoEqualsItself() {
        // Arrange
        UserStoryCreationDto reference = new UserStoryCreationDto("US001",
                "As a user, I want to create a profile", "User", 1);
        UserStoryCreationDto other = reference;
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Test to ensure that two objects with different values are not equal.
     */
    @Test
    void ensureTwoUserStoryCreationDtosWithDifferentValuesAreNotEqual() {
        // Arrange
        UserStoryCreationDto reference = new UserStoryCreationDto("US001",
                "As a user, I want to create a profile", "User", 1);
        UserStoryCreationDto other = new UserStoryCreationDto("US002",
                "As a user, I want to log in", "User", 2);
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Test to ensure that an object is not equal to null.
     */
    @Test
    void ensureUserStoryCreationDtoIsNotEqualToNull() {
        // Arrange
        UserStoryCreationDto reference = new UserStoryCreationDto("US001",
                "As a user, I want to create a profile", "User", 1);
        UserStoryCreationDto other = null;
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Test to ensure that an object is not equal to a different type of object.
     */
    @Test
    void ensureUserStoryCreationDtoIsNotEqualToDifferentTypeOfObject() {
        // Arrange
        UserStoryCreationDto reference = new UserStoryCreationDto("US001",
                "As a user, I want to create a profile", "User", 1);
        String other = "Not a UserStoryCreationDto object";
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: Test to ensure that the getPriority method returns the correct value.
     */
    @Test
    void ensureGetPriorityMethodReturnsCorrectValue() {
        // Arrange
        UserStoryCreationDto reference = new UserStoryCreationDto("US001",
                "As a user, I want to create a profile", "User", 1);
        int expected = 1;

        // Act
        int result = reference.getPriority();

        // Assert
        assertEquals(expected, result);
    }
    /**
     * Scenario 6: Test to ensure that the getPriority method returns an incorrect value.
     */
    @Test
    void ensureGetPriorityMethodReturnsIncorrectValue() {
        // Arrange
        UserStoryCreationDto reference = new UserStoryCreationDto("US001",
                "As a user, I want to create a profile", "User", 0);
        int expected = 1;

        // Act
        int result = reference.getPriority();

        // Assert
        assertNotEquals(expected, result);
    }
}