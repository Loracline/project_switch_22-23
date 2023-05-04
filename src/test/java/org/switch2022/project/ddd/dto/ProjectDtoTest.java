package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDtoTest {
     /*
      METHOD equals()
     */

    /**
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameProjectDtoEqualsItself() {
        // Arrange
        ProjectDto reference = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");
        ProjectDto other = reference;
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
    void ensureTwoProjectDtosAreNotTheSame() {
        // Arrange
        ProjectDto reference = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");

        ProjectDto other = new ProjectDto("P02", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");

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
    void ensureProjectDtoDoesNotEqualOtherTypeOfObject() {
        // Arrange
        ProjectDto reference = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");
        String other = "User";
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Verify if a ProjectDto object and a null object are not the same.
     */
    @Test
    void ensureProjectDtoDoesNotEqualNull() {
        // Arrange
        ProjectDto reference = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");
        ProjectDto other = null;
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD hashCode()
     * Scenario 1: Two ProjectDto objects are the same.
     */
    @Test
    public void ensureTwoProjectDtosHashcodeAreTheSame() {
        // Arrange
        ProjectDto projectDtoOne = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");
        ProjectDto projectDtoTwo = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");

        // Act
        int projectDtoOneHashCode = projectDtoOne.hashCode();
        int projectDtoTwoHashCode = projectDtoTwo.hashCode();

        // Assert
        assertEquals(projectDtoOneHashCode, projectDtoTwoHashCode);
    }

    /**
     * Scenario 2: Two UserStoryDto objects are not the same.
     */
    @Test
    public void ensureTwoProjectDtosHashcodeAreNotTheSame() {
        // Arrange
        ProjectDto projectDtoOne = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");
        ProjectDto projectDtoTwo = new ProjectDto("P02", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");

        // Act
        int projectDtoOneHashCode = projectDtoOne.hashCode();
        int projectDtoTwoHashCode = projectDtoTwo.hashCode();

        // Assert
        assertNotEquals(projectDtoOneHashCode, projectDtoTwoHashCode);
    }

}