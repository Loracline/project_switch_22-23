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
    /**
     * Scenario 3: Test the return equals.
     */
    @Test
    public void testReturnEquals() {
        ProjectDto project1 = new ProjectDto("code1", "Project 1", "Customer 1", "Status 1", "2022-01-01", "2022-12-31");
        ProjectDto project2 = new ProjectDto("code1", "Project 1", "Customer 1", "Status 1", "2022-01-01", "2022-12-31");
        ProjectDto project3 = new ProjectDto("code2", "Project 2", "Customer 2", "Status 2", "2022-01-01", "2022-12-31");

        // Test equal objects
        assertEquals(project1, project2);
        assertEquals(project2, project1);

        // Test not equal objects
        assertNotEquals(project1, project3);
        assertNotEquals(project3, project1);

        // Test with null
        assertNotEquals(project1, null);
        assertNotEquals(null, project1);

        // Test with different types
        assertNotEquals(project1, "Not a ProjectDto");
    }
}