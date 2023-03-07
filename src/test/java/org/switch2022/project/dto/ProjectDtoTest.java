package org.switch2022.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectDtoTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    ProjectDto projectDtoOne;
    ProjectDto projectDtoTwo;
    ProjectDto projectDtoThree;

    @BeforeEach
    void setUp() {
        projectDtoOne = new ProjectDto("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        projectDtoTwo = new ProjectDto("AA002", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        projectDtoThree = new ProjectDto("AA002", "software development management",
                "isep", "planned", "fixed cost", "fishing");
    }
    /**
     * Test to ensure the object are equals itself
     */
    @Test
    void ensureSameObjectEqualsItself() {
        assertTrue(projectDtoOne.equals(projectDtoOne));
    }
    /**
     * Test to ensure the object are not equals itself
     */
    @Test
    void ensureObjectAreNotEqualsItself() {
        assertFalse(projectDtoOne.equals(projectDtoTwo));
    }
    /**
     * Test to ensure that two projects are equals
     */
    @Test
    void ensureTwoProjectsAreEqualsItself() {
        assertTrue(projectDtoTwo.equals(projectDtoThree));
    }
    /**
     * Test to ensure that two projects does not equal null
     */
    @Test
    void ensureTwoProjectsDoesNotEqualsNull() {
        assertFalse(projectDtoOne.equals(null));
    }
    /**
     * Test to ensure that two projects does not equal other type of object(
     */
    @Test
    void ensureTwoProjectsDoesNotEqualsOtherTypeOfObject() {
        String notSameObjectType = "notSameObjectType";
        assertFalse(projectDtoOne.equals(notSameObjectType));
    }
    /**
     * Test to check the hashcode when objects are equal and unequal
     */
    @Test
    public void testHashCodeGetProjectDto() {
        ProjectDto objectOne = new ProjectDto("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        ProjectDto objectTwo = new ProjectDto("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        ProjectDto objectThree = new ProjectDto("AA003", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        // Check that equal objects have the same hash code
        assertEquals(objectOne.hashCode(), objectTwo.hashCode());
        // Check that unequal objects have different hash codes
        assertNotEquals(objectOne.hashCode(), objectThree.hashCode());
    }
}
