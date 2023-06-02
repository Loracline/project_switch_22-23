package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDtoTest {

    /**
     * METHOD: equals()
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
     * Scenario 4: Test the return equals.
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

    /**
     * Scenario 5: Verify if two objects with the same attributes are considered equal.
     */
    @Test
    void ensureTwoProjectDtoWithSameAttributesAreEqual() {
        // Arrange
        ProjectDto reference = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");

        ProjectDto other = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");

        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 6: Verify if two objects with different attribute values are considered not equal.
     */
    @Test
    void ensureTwoProjectDtoWithDifferentAttributesAreNotEqual() {
        // Arrange
        ProjectDto reference = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");

        ProjectDto other = new ProjectDto("P01", "ISEP",
                "ABC", "In Progress", "01.03.2023", "31.12.2023");

        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 7: Verify if changing the attribute values of an object makes it not equal to the original object.
     */
    @Test
    void ensureModifiedProjectDtoIsNotEqual() {
        // Arrange
        ProjectDto reference = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");

        ProjectDto modified = new ProjectDto("P01", "ISEP",
                "PTH", "In Progress", "28.02.2023", "12.15.2023");

        boolean expected = false;

        // Act
        boolean result = reference.equals(modified);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 8: Verify if two objects with different attribute casing are considered equal.
     */
    @Test
    void ensureTwoProjectDtosWithDifferentAttributeCasingAreEqual() {
        // Arrange
        ProjectDto reference = new ProjectDto("p01", "isep",
                "pth", "planned", "28.02.2023", "12.15.2023");

        ProjectDto other = new ProjectDto("P01", "ISEP",
                "PTH", "PLANNED", "28.02.2023", "12.15.2023");

        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 9: Verify if the order of attribute assignments in the constructor does not affect the equality check.
     */
    @Test
    void ensureAttributeOrderDoesNotAffectEquality() {
        // Arrange
        ProjectDto reference = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");

        ProjectDto other = new ProjectDto("28.02.2023", "ISEP",
                "PTH", "Planned", "12.15.2023", "P01");

        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 10: Verify if two objects with different start dates are considered not equal.
     */
    @Test
    void ensureProjectDtosWithDifferentStartDatesAreNotEqual() {
        // Arrange
        ProjectDto reference = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");

        ProjectDto other = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "01.03.2023", "12.15.2023");

        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 11: Verify if two objects with different code values are considered not equal.
     */
    @Test
    void ensureProjectDtosWithDifferentCodeAreNotEqual() {
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
     * Scenario 12: Verify if two objects with different project names are considered not equal.
     */
    @Test
    void ensureProjectDtosWithDifferentProjectNamesAreNotEqual() {
        // Arrange
        ProjectDto reference = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");

        ProjectDto other = new ProjectDto("P01", "Other Project",
                "PTH", "Planned", "28.02.2023", "12.15.2023");

        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 13: Verify if two objects with different customer names are considered not equal.
     */
    @Test
    void ensureProjectDtosWithDifferentCustomerNamesAreNotEqual() {
        // Arrange
        ProjectDto reference = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");

        ProjectDto other = new ProjectDto("P01", "ISEP",
                "Other Customer", "Planned", "28.02.2023", "12.15.2023");

        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 14: Verify if two objects with different statuses are considered not equal.
     */
    @Test
    void ensureProjectDtosWithDifferentStatusesAreNotEqual() {
        // Arrange
        ProjectDto reference = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");

        ProjectDto other = new ProjectDto("P01", "ISEP",
                "PTH", "In Progress", "28.02.2023", "12.15.2023");

        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 15: Verify if two objects with different end dates are considered not equal.
     */
    @Test
    void ensureProjectDtosWithDifferentEndDatesAreNotEqual() {
        // Arrange
        ProjectDto reference = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "12.15.2023");

        ProjectDto other = new ProjectDto("P01", "ISEP",
                "PTH", "Planned", "28.02.2023", "31.12.2023");

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