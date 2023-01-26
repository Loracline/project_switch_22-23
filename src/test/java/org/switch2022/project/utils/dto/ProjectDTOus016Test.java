package org.switch2022.project.utils.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectDTOus016Test {

    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    ProjectDTOus016 projectDTOOne;
    ProjectDTOus016 projectDTOTwo;
    ProjectDTOus016 projectDTOThree;

    @BeforeEach
    void setUp() {
        projectDTOOne = new ProjectDTOus016("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        projectDTOTwo = new ProjectDTOus016("AA002", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        projectDTOThree = new ProjectDTOus016("AA002", "software development management",
                "isep", "planned", "fixed cost", "fishing");
    }
    /**
     * Test to ensure the object are equals itself
     */
    @Test
    void ensureSameObjectEqualsItself() {
        assertTrue(projectDTOOne.equals(projectDTOOne));
    }
    /**
     * Test to ensure the object are not equals itself
     */
    @Test
    void ensureObjectAreNotEqualsItself() {
        assertFalse(projectDTOOne.equals(projectDTOTwo));
    }
    /**
     * Test to ensure that two projects are equals
     */
    @Test
    void ensureTwoProjectsAreEqualsItself() {
        assertTrue(projectDTOTwo.equals(projectDTOThree));
    }
    /**
     * Test to ensure that two projects does not equal null
     */
    @Test
    void ensureTwoProjectsDoesNotEqualsNull() {
        assertFalse(projectDTOOne.equals(null));
    }
    /**
     * Test to ensure that two projects does not equal other type of object(
     */
    @Test
    void ensureTwoProjectsDoesNotEqualsOtherTypeOfObject() {
        String notSameObjectType = "notSameObjectType";
        assertFalse(projectDTOOne.equals(notSameObjectType));
    }
    /**
     * Test to check the hashcode when objects are equal and unequal
     */
    @Test
    public void testHashCodeGetProjectDTO() {
        ProjectDTOus016 objectOne = new ProjectDTOus016("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        ProjectDTOus016 objectTwo = new ProjectDTOus016("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        ProjectDTOus016 objectThree = new ProjectDTOus016("AA003", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        // Check that equal objects have the same hash code
        assertEquals(objectOne.hashCode(), objectTwo.hashCode());
        // Check that unequal objects have different hash codes
        assertNotEquals(objectOne.hashCode(), objectThree.hashCode());
    }
}