package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.AcceptanceCriteria;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

class UserStoryCreationDtoTest {


    /**
     * Scenario 1: Test to ensure the same object equals itself.
     */

    @Test
    void ensureSameUserStoryCreationDtoEqualsItself() {
        // Arrange
        List<String> acceptanceCriteria = new ArrayList<>();

        UserStoryCreationDto reference = new UserStoryCreationDto("US001",
                "As a user, I want to create a profile", "User", acceptanceCriteria, 1);
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
        List<String> acceptanceCriteria = new ArrayList<>();
        List<String> acceptanceCriteriaTwo = new ArrayList<>();
        AcceptanceCriteria acceptanceCriteriaElement = mock(AcceptanceCriteria.class);
        acceptanceCriteria.add(String.valueOf(acceptanceCriteriaElement));
        UserStoryCreationDto reference = new UserStoryCreationDto("US001",
                "As a user, I want to create a profile", "User", acceptanceCriteria, 1);
        UserStoryCreationDto other = new UserStoryCreationDto("US002",
                "As a user, I want to log in", "User", acceptanceCriteriaTwo, 2);
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
        List<String> acceptanceCriteria = new ArrayList<>();
        UserStoryCreationDto reference = new UserStoryCreationDto("US001",
                "As a user, I want to create a profile", "User", acceptanceCriteria, 1);
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
        List<String> acceptanceCriteria = new ArrayList<>();
        UserStoryCreationDto reference = new UserStoryCreationDto("US001",
                "As a user, I want to create a profile", "User", acceptanceCriteria, 1);
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
        List<String> acceptanceCriteria = new ArrayList<>();
        UserStoryCreationDto reference = new UserStoryCreationDto("US001",
                "As a user, I want to create a profile", "User", acceptanceCriteria, 1);
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
        List<String> acceptanceCriteria = new ArrayList<>();
        UserStoryCreationDto reference = new UserStoryCreationDto("US001",
                "As a user, I want to create a profile", "User", acceptanceCriteria, 0);
        int expected = 1;

        // Act
        int result = reference.getPriority();

        // Assert
        assertNotEquals(expected, result);
    }

}